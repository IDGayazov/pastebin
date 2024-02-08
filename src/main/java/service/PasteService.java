package service;

import dao.PasteDao;
import dto.CreatePasteDto;
import dto.PasteDto;
import dto.UserDto;
import entity.Paste;
import exception.DateExpiredException;
import exception.InvalidHashException;
import util.HashGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PasteService {

    private static final PasteDao pasteDao = PasteDao.getInstance();
    private static final TextService textService = TextService.getInstance();
    private static final UserService userService = UserService.getInstance();
    private static final PasteService INSTANCE = new PasteService();


    private PasteService(){

    }

    public String createPaste(CreatePasteDto pasteDto, Integer userId) throws SQLException, IOException {

        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime expiredDate = createdDate.plus(pasteDto.getDuration());

        String hash;
        hash = HashGenerator.generateHash(
                pasteDto.getName() + pasteDto.getCategory() +
                        createdDate + expiredDate + pasteDto.getText()
        );

        Paste paste = Paste.builder()
                .name(pasteDto.getName())
                .category(pasteDto.getCategory())
                .hash(hash)
                .createDate(createdDate)
                .expiredDate(expiredDate)
                .build();

        textService.saveText(pasteDto.getText(), hash);
        pasteDao.save(paste);

        return paste.getHash();
    }

    public PasteDto getPasteByHash(String hash) throws SQLException, IOException, DateExpiredException, InvalidHashException {

        Optional<Paste> paste = pasteDao.findByHash(hash);

        if(!paste.isPresent()){
           throw new InvalidHashException();
        }

        Paste pasteInfo = paste.get();

        if(LocalDateTime.now().isAfter(pasteInfo.getExpiredDate())){
            throw new DateExpiredException();
        }

        UserDto user = userService.getUserById(pasteInfo.getUserId());
        String username = user.getLogin();

        String text = textService.getText(hash);

        return PasteDto.builder()
                .name(pasteInfo.getName())
                .category(pasteInfo.getCategory())
                .text(text)
                .createDate(pasteInfo.getCreateDate())
                .expiredDate(pasteInfo.getExpiredDate())
                .username(username)
                .build();
    }

    public boolean deletePasteByHash(String hash) throws SQLException {
        boolean isDeletedDB = pasteDao.deleteByHash(hash);
        boolean isDeletedText = textService.deleteText(hash);

        return isDeletedDB && isDeletedText;
    }

    public List<String> getAllHashesWithExpiredDate() throws SQLException {
        return pasteDao.findAllHashByExpiredDate();
    }

    public static PasteService getInstance(){
        return INSTANCE;
    }

}
