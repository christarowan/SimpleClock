//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class SimpleClock extends JFrame {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        JButton formatButton;
        JButton gmtButton;
        String time;
        String day;
        String date;

        private boolean is24Hour = false;
        private boolean isGMT = false;



        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(500, 200);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 49));
            timeLabel.setBackground(Color.BLUE);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            formatButton = new JButton("12-hour");
            gmtButton = new JButton ("GMT");
            formatButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    is24Hour = !is24Hour;
                    if (is24Hour) {
                        formatButton.setText("12-hour");
                    } else {
                        formatButton.setText("24-hour");
                    }
                }
            });

            gmtButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    isGMT = !isGMT;
                    if (isGMT) {
                        gmtButton.setText("Local Time");
                    } else {
                        gmtButton.setText("GMT");
                    }

                }
            });


            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(formatButton);
            this.add(gmtButton);
            this.setVisible(true);



    
            setTimer();
        }
    
        public void setTimer() {
            TimerThread timerThread = new TimerThread();

            timerThread.start();
        }
    private class TimerThread extends Thread {
        public void run() {

            while (true) {

                if (is24Hour) {
                    timeFormat = new SimpleDateFormat("HH:mm:ss a");
                }
                else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");
                }
                if (isGMT) {
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                } else {
                    timeFormat.setTimeZone(TimeZone.getDefault());
                }
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
