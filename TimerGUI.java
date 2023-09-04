import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGUI {

    static JFrame timerFrame = new JFrame();
    static JLabel timerLabel = new JLabel();
    static JButton stopButton = new JButton(new AbstractAction("STOP") {
        @Override
        public void actionPerformed(ActionEvent e) {
            stop();
        }
    });
    static JButton closeButton = new JButton(new AbstractAction("CLOSE") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    static long designatedTime;
    static long remainingTime;
    static long seconds;
    static long secondsDisplay;
    static long minutes;
    static long minutesDisplay;
    static long hours;
    static String seconds_string;
    static String minutes_string;
    static String hours_string;

    static Timer timer;

    public static void main() {
        initializeValues();

        TimerGUI timerGUI = new TimerGUI();

        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (seconds > 0) {
                    updateTime();
                    timerLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
                } else {
                    timer.cancel();
                    timerLabel.setForeground(Color.GREEN);
                    stopButton.setVisible(false);
                    closeButton.setVisible(true);
                }
            }
        };
        timer.scheduleAtFixedRate(task,0, 1000);
    }
    static void initializeValues() {
        designatedTime = Menu.inputTimeMillis;
        updateTime();
    }
    static void updateTime() {
        remainingTime = designatedTime - System.currentTimeMillis();
        seconds = remainingTime / 1000;
        secondsDisplay = seconds % 60;
        minutes = seconds / 60;
        minutesDisplay = minutes % 60;
        hours = minutes / 60;
        seconds_string = String.format("%02d", secondsDisplay);
        minutes_string = String.format("%02d", minutesDisplay);
        hours_string = String.format("%02d", hours);
    }

    TimerGUI() {
        //System.out.println("Designated Time: " + designatedTime); //
        //System.out.println("Remaining Time: " + remainingTime); //

        timerLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timerLabel.setBounds(8, 20, 300, 60);
        timerLabel.setFont(new Font("Verdana", Font.PLAIN, 60));
        timerFrame.add(timerLabel);

        stopButton.setBounds(125, 90, 50, 20);
        timerFrame.add(stopButton);

        closeButton.setBounds(125, 90, 50, 20);
        timerFrame.add(closeButton);
        closeButton.setVisible(false);

        timerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timerFrame.setSize(300, 150);
        timerFrame.setLayout(null);
        timerFrame.setTitle("Timer ^^");
        timerFrame.setVisible(true);
    }

    static void stop() {
        Menu.main(null);
        timer.cancel();
        timerFrame.setVisible(false); // sus
    }


}
