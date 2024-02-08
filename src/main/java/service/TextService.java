package service;

import util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TextService {

    private static final TextService INSTANCE = new TextService();
    private static final String URL = PropertiesUtil.get("text.base.url") + "/%s.txt";

    private TextService(){

    }
    public void saveText(String text, String hash) throws IOException {
        Path path = Paths.get(String.format(URL, hash));
        Files.write(path, List.of(text), StandardCharsets.UTF_8);
    }

    public String getText(String hash) throws IOException {
        Path path = Paths.get(String.format(URL, hash));
        return Files.lines(path, StandardCharsets.UTF_8)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public boolean deleteText(String hash){
        File deletingFile = new File(String.format(URL, hash));
        return deletingFile.delete();
    }

    public static TextService getInstance(){
        return INSTANCE;
    }

}
