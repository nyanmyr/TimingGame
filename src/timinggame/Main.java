package timinggame;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public final class Main extends javax.swing.JFrame {

    int score;
    
    int speed;
    
    int zoneStart;
    int zoneEnd;
    
    Dimension screenSize;
    Random randomizer = new Random();
    Timer timer = new Timer();

    public Main() {
        initComponents();

        label_Score.setText("SCORE");
        label_Message.setText("TIME IT RIGHT! DON'T FAIL.");

        screenSize = panel_Main.getSize();

        Start();
        
        RandomizeZone();
    }

    public void Start() {
        
        speed = 10;
    }
    
    public void RandomizeZone() {

        int randomPosition = randomizer.nextInt(-235, 130);

        label_Indicator.setBounds(
                (screenSize.width / 2) + (randomPosition),
                (screenSize.height / 2) - (29 / 2),
                100, 24);

        System.out.println("Starts at: " + randomPosition);
        System.out.println("Actual: " + (int) (((80.00 / 365.00) * (randomPosition)) + 51.51));
        System.out.println("Ends: " + (int) (((80.00 / 365.00) * (randomPosition)) + 71.51));

        zoneStart = (int) (((80.00 / 365.00) * (randomPosition)) + 51.51);
        zoneEnd = (int) (((80.00 / 365.00) * (randomPosition)) + 71.51);

        timer = new Timer();

        TimerTask timerTask = new TimerTask() {

            boolean reversed = false;

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

        timer.scheduleAtFixedRate(timerTask, 0, speed - (score / 4));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panel_Main = new javax.swing.JPanel();
        slider_Slider = new javax.swing.JSlider();
        label_Indicator = new javax.swing.JLabel();
        label_Score = new javax.swing.JLabel();
        button_Time = new javax.swing.JButton();
        label_Message = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));
        setSize(new java.awt.Dimension(500, 300));

        panel_Main.setFocusable(false);
        panel_Main.setMinimumSize(new java.awt.Dimension(400, 300));
        panel_Main.setPreferredSize(new java.awt.Dimension(500, 300));
        panel_Main.setRequestFocusEnabled(false);
        panel_Main.setVerifyInputWhenFocusTarget(false);
        panel_Main.setLayout(null);

        slider_Slider.setBackground(new java.awt.Color(255, 255, 255, 0));
        slider_Slider.setMajorTickSpacing(10);
        slider_Slider.setMinorTickSpacing(1);
        slider_Slider.setEnabled(false);
        slider_Slider.setFocusable(false);
        slider_Slider.setName(""); // NOI18N
        slider_Slider.setRequestFocusEnabled(false);
        slider_Slider.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(slider_Slider);
        slider_Slider.setBounds(0, 120, 480, 60);
        slider_Slider.setOpaque(false);

        label_Indicator.setBackground(new java.awt.Color(0, 102, 204));
        label_Indicator.setFocusable(false);
        label_Indicator.setOpaque(true);
        label_Indicator.setRequestFocusEnabled(false);
        label_Indicator.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Indicator);
        label_Indicator.setBounds(10, 110, 466, 80);

        label_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Score.setText("SCORE");
        label_Score.setFocusable(false);
        label_Score.setRequestFocusEnabled(false);
        label_Score.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Score);
        label_Score.setBounds(10, 10, 470, 40);

        button_Time.setText("TIME");
        panel_Main.add(button_Time);
        button_Time.setBounds(175, 200, 130, 40);
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

        label_Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Message.setText("MESSAGE");
        label_Message.setFocusable(false);
        label_Message.setRequestFocusEnabled(false);
        label_Message.setVerifyInputWhenFocusTarget(false);
        panel_Main.add(label_Message);
        label_Message.setBounds(10, 50, 470, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        if (slider_Slider.getValue() >= zoneStart && slider_Slider.getValue() <= zoneEnd) {
            label_Score.setText("SCORE: " + ++score);
            label_Message.setText("WIN");
            timer.cancel();
            RandomizeZone();
        } else {
            label_Message.setText("FAIL");
            score = 0;
            label_Score.setText("SCORE: " + score);
            timer.cancel();
            Start();
            RandomizeZone();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Time;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel label_Indicator;
    private javax.swing.JLabel label_Message;
    private javax.swing.JLabel label_Score;
    private javax.swing.JPanel panel_Main;
    private javax.swing.JSlider slider_Slider;
    // End of variables declaration//GEN-END:variables
}
