package timinggame;

/*
TODO:
- difficulties (EASY, NORMAL, HARD, IMPOSSIBLE)
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public final class Main extends javax.swing.JFrame {

    // <editor-fold desc="color constants">
    final Color NORMAL_COLOR = new java.awt.Color(0, 102, 204);
    final Color HARDCORE_COLOR = new java.awt.Color(204, 102, 0);
    // </editor-fold>

    boolean gaming;
    boolean DEBUGGING = false;

    // <editor-fold desc="scoring variables">
    int score;

    // high scores
    int highestScore = HighscoreManager.loadHighScore();
    int hardcoreHighestScore = HardcoreHighscoreManager.loadHighScore();

    // recent && average scores
    ArrayList<Integer> recentScores = RecentScoreManager.loadRecentScores();
    float averageScore = 0;
    ArrayList<Integer> hardcoreRecentScores = HardcoreRecentScoreManager.loadRecentScores();
    float hardcoreAverageScore = 0;
    // </editor-fold>

    // <editor-fold desc="speed variables">
    final int STARTING_SPEED = 10;
    int score_divisor = 3; // factor used to reduce speed as score increases
    int speed;
    // </editor-fold>

    // <editor-fold desc="screen size variables">
    final Dimension INDICATOR_MAXED_POSITION = new Dimension(10, 110);
    final Dimension INDICATOR_MAXED_SIZED = new Dimension(480, 80);
    Dimension screenSize;
    // </editor-fold>

    // <editor-fold desc="randomizer variables">
    Random randomizer = new Random();
    int zoneStart;
    int zoneEnd;
    // </editor-fold>

    // <editor-fold desc="timer variables">
    Timer timer = new Timer();
    TimerTask timerTask;
    boolean reversed;
    // </editor-fold>

    // <editor-fold desc="hardcore variables">
    boolean isHardcore = false;
    int shrinkFactor = 0;
    // </editor-fold>

    public Main() {
        initComponents();

        // Set the icon image
        screenSize = panel_Main.getSize();

        score = 0;
        start();

        label_CurrentValue.setVisible(DEBUGGING);
        label_StartValue.setVisible(DEBUGGING);
        label_EndValue.setVisible(DEBUGGING);
    }

    private void start() {
        gaming = false;
        speed = STARTING_SPEED;

        label_Message.setText("TIME IT RIGHT! DON'T FAIL.");

        label_Indicator.setBounds(
                INDICATOR_MAXED_POSITION.width, INDICATOR_MAXED_POSITION.height,
                INDICATOR_MAXED_SIZED.width, INDICATOR_MAXED_SIZED.height);
        slider_Slider.setValue(50);

        button_Mode.setVisible(true);

        calculateAverage();
        setHighestScoreLabel();

        shrinkFactor = 0;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panel_Main = new javax.swing.JPanel();
        slider_Slider = new javax.swing.JSlider();
        button_Mode = new javax.swing.JButton();
        button_Time = new javax.swing.JButton();
        label_Indicator = new javax.swing.JLabel();
        label_AverageScoreText = new javax.swing.JLabel();
        label_AverageScore = new javax.swing.JLabel();
        label_HighestScore = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        label_CurrentValue1 = new javax.swing.JLabel();
        label_CurrentValue = new javax.swing.JLabel();
        label_EndValue = new javax.swing.JLabel();
        label_StartValue = new javax.swing.JLabel();
        label_Message = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nyanmyr's Timing Game");
        setIconImage(new ImageIcon(getClass().getResource("/timinggame/resources/icon.png")).getImage());
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
        slider_Slider.setFocusable(false);
        slider_Slider.setName(""); // NOI18N
        slider_Slider.setRequestFocusEnabled(false);
        slider_Slider.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(slider_Slider);
        slider_Slider.setBounds(0, 120, 500, 60);
        slider_Slider.setOpaque(false);

        button_Mode.setBackground(NORMAL_COLOR);
        button_Mode.setForeground(new java.awt.Color(244, 244, 244));
        button_Mode.setText("Normal");
        button_Mode.setFocusPainted(false);
        button_Mode.setFocusable(false);
        panel_Main.add(button_Mode);
        button_Mode.setBounds(10, 10, 110, 30);
        button_Mode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ModeChange(evt);
            }
        });

        button_Time.setBackground(NORMAL_COLOR);
        button_Time.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        button_Time.setForeground(new java.awt.Color(244, 244, 244));
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

        label_Indicator.setBackground(NORMAL_COLOR);
        label_Indicator.setFocusable(false);
        label_Indicator.setOpaque(true);
        label_Indicator.setRequestFocusEnabled(false);
        label_Indicator.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Indicator);
        label_Indicator.setBounds(10, 110, 480, 80);

        label_AverageScoreText.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_AverageScoreText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_AverageScoreText.setText("(WITHIN 10 RUNS)");
        label_AverageScoreText.setFocusable(false);
        label_AverageScoreText.setRequestFocusEnabled(false);
        label_AverageScoreText.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_AverageScoreText);
        label_AverageScoreText.setBounds(320, 280, 170, 20);

        label_AverageScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_AverageScore.setText("AVERAGE SCORE: 0");
        label_AverageScore.setFocusable(false);
        label_AverageScore.setRequestFocusEnabled(false);
        label_AverageScore.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_AverageScore);
        label_AverageScore.setBounds(320, 240, 170, 40);

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

        label_CurrentValue1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        label_CurrentValue1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_CurrentValue1.setText("VERSION 0.2.1 (RELEASE");
        label_CurrentValue1.setFocusable(false);
        label_CurrentValue1.setRequestFocusEnabled(false);
        label_CurrentValue1.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_CurrentValue1);
        label_CurrentValue1.setBounds(180, 240, 140, 40);

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

    // <editor-fold desc="game loop functions">
    private void randomizeZone() {

        int randomPosition = randomizer.nextInt(-249, 134);
//        int randomPosition = 134;

        label_Indicator.setBounds(
                (screenSize.width / 2) + (randomPosition) + (shrinkFactor * 10),
                (screenSize.height / 2) - (56 / 2),
                100 - (shrinkFactor * 10), 24);

        if (isHardcore && score > 1 && shrinkFactor < 5 && score % 3 == 0) {
            playSound(SoundFiles.SHRINK.getFilePath());
            shrinkFactor++;
        }

//        System.out.println("Starts at: " + randomPosition);
//        System.out.println("Actual: " + (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 53));
//        System.out.println("Ends: " + (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 73));
        zoneStart = (int) (((80.00 / (249.00 + 134.00)) * (randomPosition)) + 53);
        zoneEnd = (int) ((((80.00 / (249.00 + 134.00)) * (randomPosition)) + 73)) - (shrinkFactor * 2);

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

        int x = speed - (score / score_divisor) < 1 ? 1 : speed - (score / score_divisor);
//        System.out.println("Speed: " + x);
        timer.scheduleAtFixedRate(timerTask, 0, x);
    }

    private void timeAction() {
        int test = slider_Slider.getValue();

        if (DEBUGGING) {
            label_CurrentValue.setText(String.format("CURRENT VALUE: %s (%S)", slider_Slider.getValue(), (test >= zoneStart && test <= zoneEnd)));
            label_StartValue.setText(String.format("START VALUE: %s", zoneStart));
            label_EndValue.setText(String.format("END VALUE: %s", zoneEnd));
        }

        if (!gaming) {
            moveZone();
            gaming = true;
            playSound(SoundFiles.START.getFilePath());
            button_Mode.setVisible(false);
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

                addRecentScore(score);
                updateScore();

                start();
            }
        }

        if (gaming) {
            randomizeZone();
        }
    }
    // </editor-fold>

    // <editor-fold desc="input functions">
    private void panel_SpacePressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            timeAction();
        }
    }

    private void button_TimeClicked(java.awt.event.ActionEvent evt) {
        timeAction();
    }
    // </editor-fold>

    public static void playSound(URL filePath) {
        try {
            if (filePath == null) {
                System.err.println("Sound URL is null.");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(filePath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Optional cleanup listener
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start(); // Play asynchronously
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    // <editor-fold desc="scoring functions">
    private void addRecentScore(int score) {

        if (isHardcore) {
            hardcoreRecentScores.addFirst(score);
            if (hardcoreRecentScores.size() > 10) {
                hardcoreRecentScores.remove(10);
            }

            calculateAverage();

            HardcoreRecentScoreManager.saveRecentScores(hardcoreRecentScores);
            label_AverageScore.setText(String.format("AVERAGE SCORE: %.2f", hardcoreAverageScore));

            return;
        }

        recentScores.addFirst(score);
        if (recentScores.size() > 10) {
            recentScores.remove(10);
        }

        calculateAverage();

        RecentScoreManager.saveRecentScores(recentScores);
        label_AverageScore.setText(String.format("AVERAGE SCORE: %.2f", averageScore));

    }

    private void calculateAverage() {
        if (isHardcore) {
            if (!hardcoreRecentScores.isEmpty()) {
                float sum = 0;
                for (int i : hardcoreRecentScores) {
                    sum += i;
                }
                hardcoreAverageScore = sum / hardcoreRecentScores.size();
            } else {
                hardcoreAverageScore = 0;
            }
            return;
        }

        if (!recentScores.isEmpty()) {
            float sum = 0;
            for (int i : recentScores) {
                sum += i;
            }
            averageScore = sum / recentScores.size();
        } else {
            averageScore = 0;
        }
    }

    private void updateScore() {
        label_Score.setText("LAST SCORE: " + score);

        if (isHardcore && score > hardcoreHighestScore) {
            label_Score.setText("PREVIOUS HIGHEST SCORE BEATEN! " + score);
            hardcoreHighestScore = score;
            label_HighestScore.setText("HIGHEST SCORE: " + hardcoreHighestScore);
            HardcoreHighscoreManager.saveHighScore(hardcoreHighestScore);
            score = 0;

            return;
        }

        if (score > highestScore) {
            label_Score.setText("PREVIOUS HIGHEST SCORE BEATEN! " + score);
            highestScore = score;
            label_HighestScore.setText("HIGHEST SCORE: " + highestScore);
            HighscoreManager.saveHighScore(highestScore);
        }

        score = 0;
    }

    private void setHighestScoreLabel() {
        if (isHardcore) {
            label_HighestScore.setText("HIGHEST SCORE: " + hardcoreHighestScore);
            label_AverageScore.setText(String.format("AVERAGE SCORE: %.2f", hardcoreAverageScore));
        } else {
            label_HighestScore.setText("HIGHEST SCORE: " + highestScore);
            label_AverageScore.setText(String.format("AVERAGE SCORE: %.2f", averageScore));
        }

    }
    // </editor-fold>

    private void button_ModeChange(java.awt.event.ActionEvent evt) {

        if (button_Mode.getText().equals("Normal")) {
            isHardcore = true;
            button_Mode.setText("Hardcore");
            label_Message.setText("<html>"
                    + "<p style=\"text-align: center;\">"
                    + "The zone will move faster every two hits and shrink every three hits! Are you up for the test?"
                    + "</p>"
                    + "</html>");
            label_Score.setText("Prepare for Hardcore Mode");
        } else {
            isHardcore = false;
            button_Mode.setText("Normal");
            label_Message.setText("The zone will move faster every three hits! Test your reflexes!");
            label_Score.setText("Welcome to Normal Mode");
        }

        setHighestScoreLabel();
        changeColors(isHardcore ? HARDCORE_COLOR : NORMAL_COLOR);
        score_divisor = isHardcore ? 2 : 3;
    }

    private void changeColors(Color color) {
        button_Mode.setBackground(color);
        button_Time.setBackground(color);
        label_Indicator.setBackground(color);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    // <editor-fold desc="swing variables declaration">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Mode;
    private javax.swing.JButton button_Time;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel label_AverageScore;
    private javax.swing.JLabel label_AverageScoreText;
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
    // </editor-fold>
}
