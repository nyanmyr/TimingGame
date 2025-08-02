package timinggame;

import java.net.URL;

public enum SoundFiles {

    CUT("/timinggame/resources/cut.wav"),
    FAIL("/timinggame/resources/fail.wav"),
    START("/timinggame/resources/start.wav");
    
    private final URL FILE_PATH;
    
    SoundFiles(String FILE_PATH) {
        this.FILE_PATH = SoundFiles.class.getResource(FILE_PATH);
        if (this.FILE_PATH == null) {
            throw new IllegalArgumentException("Resource not found: " + FILE_PATH);
        }
    }
    
    public URL getFilePath() {
        return FILE_PATH;
    }
    
}
