package service.CleanUpService;

import service.PasteService;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class CleanUpTask implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(CleanUpTask.class.getName());
    private static final PasteService pasteService = PasteService.getInstance();
    @Override
    public void run() {
        try {
            List<String> expiredHashes = pasteService.getAllHashesWithExpiredDate();
            for(String hash: expiredHashes){
                if(pasteService.deletePasteByHash(hash)){
                    LOGGER.info("Paste: " + hash + " was deleted");
                }else{
                    LOGGER.warning("Problem with deleting text: " + hash);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
