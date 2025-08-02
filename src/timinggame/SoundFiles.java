package timinggame;

public enum SoundFiles {
    
    CUT("src\\timinggame\\sounds\\cut.wav"),
    FAIL("src\\timinggame\\sounds\\fail.wav"),
    START("src\\timinggame\\sounds\\start.wav");
    
    private final String FILE_PATH;
    
    SoundFiles(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }
    
    public String getFilePath() {
        return FILE_PATH;
    }
    
}
