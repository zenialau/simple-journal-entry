import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu implements ActionListener {

    JFrame menuFrame = new JFrame();
    JLabel inputLabel = new JLabel("Timer until ");
    JTextField hourField = new JTextField();
    JLabel colonLabel = new JLabel(":");
    JTextField minuteField = new JTextField();
    JButton startButton = new JButton("START");
    public static long inputTimeMillis;

    public static void main(String[] args) { //
        Menu menu = new Menu();
    }

    Menu() {

        inputLabel.setBounds(30, 40, 110, 20);
        inputLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        menuFrame.add(inputLabel);

        hourField.setBounds(140, 40, 50, 25);
        menuFrame.add(hourField);

        colonLabel.setBounds(190, 40, 10, 20);
        colonLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        menuFrame.add(colonLabel);

        minuteField.setBounds(200, 40, 50, 25);
        menuFrame.add(minuteField);

        startButton.setBounds(125, 90, 50, 20);
        startButton.addActionListener(this);
        menuFrame.add(startButton);

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(300, 150);
        menuFrame.setLayout(null);
        menuFrame.setTitle("Timer >>");
        menuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        start();
        menuFrame.setVisible(false); // very sus
    }

    void calculateInput() {

        Date today = new Date();
        Format year = new SimpleDateFormat("yyyy");
        Format month = new SimpleDateFormat("MM");
        Format day = new SimpleDateFormat("dd");

        String strYear = year.format(today);
        String strMonth = month.format(today);
        String strDay = day.format(today);

        String strHour = hourField.getText();
        String strMinute = minuteField.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateString = strDay + "-" + strMonth + "-" + strYear + " " + strHour + ":" + strMinute + ":00";
        //System.out.println("Input: " + dateString); //

        try {
            Date inputTime = sdf.parse(dateString);
            inputTimeMillis = inputTime.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void start() {
        calculateInput();
        TimerGUI.main();
    }

}
