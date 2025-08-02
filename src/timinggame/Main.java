package timinggame;

/*
TODO:
- difficulties (EASY, NORMAL, HARD, IMPOSSIBLE)
 */
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public final class Main extends javax.swing.JFrame {

    int highestScore = HighscoreManager.loadHighScore();
    int score;

    final int STARTING_SPEED = 10;
    final int SCORE_DIVSOR = 3; // factor used to reduce speed as score increases
    int speed;

    final Dimension INDICATOR_MAXED_POSITION = new Dimension(10, 110);
    final Dimension INDICATOR_MAXED_SIZED = new Dimension(480, 80);
    int zoneStart;
    int zoneEnd;

    Dimension screenSize;
    Random randomizer = new Random();
    
    Timer timer = new Timer();
    TimerTask timerTask;
    boolean reversed;

    boolean gaming;

    boolean debugging;

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                System.err.println("File not found: " + filePath);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Optional: auto-close the clip when done
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public Main() {
        initComponents();

        screenSize = panel_Main.getSize();

        score = 0;
        start();

        label_HighestScore.setText("HIGHEST SCORE: " + highestScore);

        debugging = false;
        label_CurrentValue.setVisible(debugging);
        label_StartValue.setVisible(debugging);
        label_EndValue.setVisible(debugging);
    }

    public void start() {
        gaming = false;
        speed = STARTING_SPEED;

        label_Message.setText("TIME IT RIGHT! DON'T FAIL.");

        label_Indicator.setBounds(
                INDICATOR_MAXED_POSITION.width, INDICATOR_MAXED_POSITION.height,
                INDICATOR_MAXED_SIZED.width, INDICATOR_MAXED_SIZED.height);
        slider_Slider.setValue(50);
    }

    public void randomizeZone() {

        int randomPosition = randomizer.nextInt(-249, 134);

        label_Indicator.setBounds(
                (screenSize.width / 2) + (randomPosition),
                (screenSize.height / 2) - (56 / 2),
                100, 24);

//        System.out.println("Starts at: " + randomPosition);
//        System.out.println("Actual: " + (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 53));
//        System.out.println("Ends: " + (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 73));
        zoneStart = (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 53);
        zoneEnd = (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 73);

    }

    private void moveZone() {

        timer = new Timer();

        timerTask = new TimerTask() {

            @Override
            public void run() {
                if (reversed) {
                    slider_Slider.setValue(slider_Slider.getValue() - 1);
                    if (slider_Slider.getValue() == 0) {
                        slider_Slider.setValue(slider_Slider.getValue() + 1);
                        reversed = false;
                    }
                } else {
                    slider_Slider.setValue(slider_Slider.getValue() + 1);
                    if (slider_Slider.getValue() == slider_Slider.getMaximum()) {
                        slider_Slider.setValue(slider_Slider.getValue() - 1);
                        reversed = true;
                    }
                }
            }
        };

        int x = speed - (score / SCORE_DIVSOR) < 1 ? 1 : speed - (score / SCORE_DIVSOR);
//        System.out.println("Speed: " + x);
        timer.scheduleAtFixedRate(timerTask, 0, x);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panel_Main = new javax.swing.JPanel();
        slider_Slider = new javax.swing.JSlider();
        label_Indicator = new javax.swing.JLabel();
        label_HighestScore = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        button_Time = new javax.swing.JButton();
        label_CurrentValue1 = new javax.swing.JLabel();
        label_CurrentValue = new javax.swing.JLabel();
        label_EndValue = new javax.swing.JLabel();
        label_StartValue = new javax.swing.JLabel();
        label_Message = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nyanmyr's Timing Game");
        setResizable(false);
        setSize(new java.awt.Dimension(515, 330));

        panel_Main.setFocusable(false);
        panel_Main.setMinimumSize(new java.awt.Dimension(515, 330));
        panel_Main.setPreferredSize(new java.awt.Dimension(515, 330));
        panel_Main.setRequestFocusEnabled(false);
        panel_Main.setVerifyInputWhenFocusTarget(false);
        panel_Main.setLayout(null);

        slider_Slider.setBackground(new java.awt.Color(255, 255, 255, 0));
        slider_Slider.setMajorTickSpacing(10);
        slider_Slider.setMinorTickSpacing(1);
        slider_Slider.setValue(22);
        slider_Slider.setFocusable(false);
        slider_Slider.setName(""); // NOI18N
        slider_Slider.setRequestFocusEnabled(false);
        slider_Slider.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(slider_Slider);
        slider_Slider.setBounds(0, 120, 500, 60);
        slider_Slider.setOpaque(false);

        label_Indicator.setBackground(new java.awt.Color(0, 102, 204));
        label_Indicator.setFocusable(false);
        label_Indicator.setOpaque(true);
        label_Indicator.setRequestFocusEnabled(false);
        label_Indicator.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Indicator);
        label_Indicator.setBounds(10, 110, 480, 80);

        label_HighestScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_HighestScore.setText("HIGHEST SCORE: 0");
        label_HighestScore.setFocusable(false);
        label_HighestScore.setRequestFocusEnabled(false);
        label_HighestScore.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_HighestScore);
        label_HighestScore.setBounds(320, 200, 170, 40);

        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("SCORE: 0");
        label_Score.setFocusable(false);
        label_Score.setRequestFocusEnabled(false);
        label_Score.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Score);
        label_Score.setBounds(10, 10, 480, 40);

        button_Time.setText("TIME");
        panel_Main.add(button_Time);
        button_Time.setBounds(180, 200, 140, 40);
        button_Time.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                panel_SpacePressed(evt);
            }
        });

        button_Time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TimeClicked(evt);
            }
        });

        label_CurrentValue1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_CurrentValue1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CurrentValue1.setText("VERSION 0.1.2 (PROTOTYPE)");
        label_CurrentValue1.setFocusable(false);
        label_CurrentValue1.setRequestFocusEnabled(false);
        label_CurrentValue1.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_CurrentValue1);
        label_CurrentValue1.setBounds(180, 250, 140, 30);

        label_CurrentValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CurrentValue.setText("CURRENT VALUE:");
        label_CurrentValue.setFocusable(false);
        label_CurrentValue.setRequestFocusEnabled(false);
        label_CurrentValue.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_CurrentValue);
        label_CurrentValue.setBounds(10, 260, 160, 30);

        label_EndValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_EndValue.setText("END VALUE:");
        label_EndValue.setFocusable(false);
        label_EndValue.setRequestFocusEnabled(false);
        label_EndValue.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_EndValue);
        label_EndValue.setBounds(10, 230, 160, 30);

        label_StartValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_StartValue.setText("START VALUE:");
        label_StartValue.setFocusable(false);
        label_StartValue.setRequestFocusEnabled(false);
        label_StartValue.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_StartValue);
        label_StartValue.setBounds(10, 200, 160, 30);

        label_Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Message.setText("MESSAGE");
        label_Message.setFocusable(false);
        label_Message.setRequestFocusEnabled(false);
        label_Message.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Message);
        label_Message.setBounds(10, 50, 480, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    private void panel_SpacePressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TimeAction();
        }
    }

    private void button_TimeClicked(java.awt.event.ActionEvent evt) {
        TimeAction();
    }

    private void TimeAction() {
        int test = slider_Slider.getValue();

        if (debugging) {
            label_CurrentValue.setText(String.format("CURRENT VALUE: %s (%S)", slider_Slider.getValue(), (test >= zoneStart && test <= zoneEnd)));
            label_StartValue.setText(String.format("START VALUE: %s", zoneStart));
            label_EndValue.setText(String.format("END VALUE: %s", zoneEnd));
        }

        if (!gaming) {
            moveZone();
            gaming = true;
            playSound(SoundFiles.START.getFilePath());
        } else {
            timer.cancel();
            if (test >= zoneStart && test <= zoneEnd) {
                playSound(SoundFiles.CUT.getFilePath());

                label_Message.setText("WIN");
                score++;
                label_Score.setText("SCORE: " + score);

                moveZone();
            } else {
                playSound(SoundFiles.FAIL.getFilePath());

                label_Message.setText("FAIL");
                label_Score.setText("LAST SCORE: " + score);
                if (score > highestScore) {
                    highestScore = score;
                }

                label_HighestScore.setText("HIGHEST SCORE: " + highestScore);
                HighscoreManager.saveHighScore(highestScore);

                score = 0;

                start();
            }
        }

        if (gaming) {
            randomizeZone();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Time;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel label_CurrentValue;
    private javax.swing.JLabel label_CurrentValue1;
    private javax.swing.JLabel label_EndValue;
    private javax.swing.JLabel label_HighestScore;
    private javax.swing.JLabel label_Indicator;
    private javax.swing.JLabel label_Message;
    private javax.swing.JLabel label_Score;
    private javax.swing.JLabel label_StartValue;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JSlider slider_Slider;
    // End of variables declaration//GEN-END:variables
}
