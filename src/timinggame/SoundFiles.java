package timinggame;

public enum SoundFiles {
    
    CUT("src\\timinggame\\resources\\cut.wav"),
    FAIL("src\\timinggame\\resources\\fail.wav"),
    START("src\\timinggame\\resources\\start.wav");
    
    private final String FILE_PATH;
    
    SoundFiles(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }
    
    public String getFilePath() {
        return FILE_PATH;
    }
    
}
