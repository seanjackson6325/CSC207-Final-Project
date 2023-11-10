package view;

import interface_adapter.PlaceholderViewModel;

import javax.swing.*;
import java.awt.*;

public class PlaceholderView {

    private final JFrame jFrame;
    private final JPanel jPanel;
    private final CardLayout cardLayout;

    private final PlaceholderViewModel placeHolderViewModel;

    public PlaceholderView(PlaceholderViewModel placeholderViewModel)
    {
        this.placeHolderViewModel = placeholderViewModel;
        this.jFrame = new JFrame("Test View");
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setFocusable(true);
        this.jFrame.setResizable(true);
        this.jFrame.setSize(new Dimension(640, 360));
        this.jFrame.setPreferredSize(new Dimension(640, 360));
        this.jFrame.setVisible(true);
        this.cardLayout = new CardLayout();
        this.jPanel = new JPanel();
        this.jFrame.add(jPanel);

        //this.jPanel.add(new JButton("Hello"));
        jPanel.add(new JButton(placeholderViewModel.LOGIN_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.SIGNUP_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.ADDTODO_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.REMOVETODO_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.ADDGROUPTODO_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.CREATEGROUP_BUTTON_STRING));
        jPanel.add(new JButton(placeholderViewModel.MANAGEMEMBERS_BUTTON_STRING));
    }

}
