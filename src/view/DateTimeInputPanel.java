package view;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DateTimeInputPanel extends JPanel {

    private static final String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    private static final Integer[] minutes = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53, 54, 55, 56, 57, 58, 59
    };

    public static final Integer[] hours = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23
    };


    private JComboBox<String> monthsComboBox;
    private JComboBox<Integer> minuteComboBox;

    private JComboBox<Integer> hourComboBox;
    private JTextField dayTextField;

    private JTextField yearTextField;

    private int month;
    private int minute;
    private int hour;
    private int day;
    private int year;

    private final List<String> validDays = Arrays.asList(new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "31", "32"});

    private final List<String> validChars = Arrays.asList(new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    });

    public DateTimeInputPanel(LocalDateTime time)
    {
        dayTextField = new JTextField(2);
        monthsComboBox = new JComboBox<>(months);
        yearTextField = new JTextField(4);
        hourComboBox = new JComboBox<>(hours);
        minuteComboBox = new JComboBox<>(minutes);

        this.add(new JLabel("Year: "));
        this.add(yearTextField);
        this.add(new JLabel("Month: "));
        this.add(monthsComboBox);
        this.add(new JLabel("Day: "));
        this.add(dayTextField);
        this.add(new JLabel("Time: "));
        this.add(hourComboBox);
        this.add(new JLabel(":"));
        this.add(minuteComboBox);

        month = 1;
        minute = 0;
        hour = 0;
        day = -1;
        year = -1;

        monthsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                month = monthsComboBox.getSelectedIndex() + 1;
            }
        });

        minuteComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                minute = minutes[minuteComboBox.getSelectedIndex()];
            }
        });

        hourComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hour = hours[hourComboBox.getSelectedIndex()];
            }
        });
    }

    public LocalDateTime getLocalDateTime()
    {
        return LocalDateTime.of(
                Integer.parseInt(yearTextField.getText()),
                month,
                Integer.parseInt(dayTextField.getText()),
                hour,
                minute);
    }

    public boolean isValidInput()
    {
        boolean yearValid = false;
        for(int i = 0; i < yearTextField.getText().length(); i++)
        {
            String charString = "" + yearTextField.getText().charAt(i);
            if(!validChars.contains(charString))
            {
                return false;
            }
        }
        yearValid = true;
        boolean dayValid = validDays.contains(dayTextField.getText());
        return yearValid && dayValid;
    }

}

