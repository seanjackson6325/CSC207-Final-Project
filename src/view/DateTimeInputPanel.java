package view;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;

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

    JComboBox<String> monthsComboBox;
    JTextField minuteTextField;

    JTextField hourTextField;
    JTextField dayTextField;

    JTextField yearTextField;

    JComboBox<String> am_pm;


    public DateTimeInputPanel(LocalDateTime time)
    {
        dayTextField = new JTextField(2);
        monthsComboBox = new JComboBox<>(months);
        yearTextField = new JTextField(4);
        hourTextField = new JTextField(2);
        minuteTextField = new JTextField(2);
        am_pm = new JComboBox<>(new String[] {"AM", "PM"});

        this.add(new JLabel("Year: "));
        this.add(yearTextField);
        this.add(new JLabel("Month: "));
        this.add(monthsComboBox);
        this.add(new JLabel("Day: "));
        this.add(dayTextField);
        this.add(new JLabel("Time: "));
        this.add(hourTextField);
        this.add(new JLabel(":"));
        this.add(minuteTextField);
        this.add(am_pm);
    }

    public LocalDateTime getLocalDateTime()
    {
        int year = Integer.parseInt(yearTextField.getText());
        int month = monthsComboBox.getSelectedIndex() + 1;
        int day = 1;
        int hour = 23;
        int minute = 59;
        return LocalDateTime.of(year, month, day, hour, minute);
    }


}

