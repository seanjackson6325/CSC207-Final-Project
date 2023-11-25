package view.team;

import interface_adapter.team.TeamViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamView extends JPanel {

    /**
     *  FOR THE TOOLBAR
     */
    JMenuBar menuBar;
    JMenu mainMenu, viewMenu, weatherMenu, teamMenu;
    JMenuItem logoutMainMenuItem, personalViewMenuItem, weatherViewMenuItem, addTeamMenuItem;
    JMenu switchTeamSubMenu;

    /**
     *  FOR THE TASK SELECTION MENU
     * */
    JLabel todoSelectorLabel;
    JPanel todoSelectorPanel;
    JPanel todoButtonsPanel;
    JButton addTodoButton, removeTodoButton, editTodoButton, completeTodoButton;
    JList<String> todoNameList;
    JScrollPane todoNameListScroller;

    /**
     * FOR THE TASK DESCRIPTION MENU
     */
    JLabel todoDescriptionLabel;
    JPanel todoInfoPanel;
    JTextPane todoDescriptionTextPane;
    JPanel todoTimePanel;
    JEditorPane todoStartTimeTextPane;
    JEditorPane todoEndTimeTextPane;
    JPanel todoStatusPanel;
    JEditorPane todoStatusTextPane;
    JEditorPane todoAssignedTextPane;

    /**
     * FOR THE TEAM MENU
     */

    JLabel teamSelectorLabel;
    JPanel teamMemberSelectorPanel;
    JList<String> teamMembersList;
    JScrollPane teamMemberListScroller;
    JPanel teamButtonsPanel;
    JButton addTeamMemberButton;
    JButton removeTeamMemberButton;


    public TeamView()
    {
        /**
         * INITIALIZE TASK SELECTION FIELDS
         */

        // initialize buttons and the panel that holds them
        todoButtonsPanel = new JPanel();
        addTodoButton = new JButton(TeamViewModel.ADD_TODO_BUTTON_STRING);
        removeTodoButton = new JButton(TeamViewModel.REMOVE_TODO_BUTTON_STRING);
        editTodoButton = new JButton(TeamViewModel.EDIT_TODO_BUTTON_STRING);
        completeTodoButton = new JButton(TeamViewModel.COMPLETE_TODO_BUTTON);
        todoButtonsPanel.add(addTodoButton);
        todoButtonsPanel.add(removeTodoButton);
        todoButtonsPanel.add(editTodoButton);
        todoButtonsPanel.add(completeTodoButton);

        // initialize the task list and scroll pane
        todoNameList = new JList<>();
        todoNameList.setListData(new String[] {"Test1", "Test2", "Test3"});
        todoNameListScroller = new JScrollPane(todoNameList);
        todoNameListScroller.setPreferredSize(new Dimension(TeamViewModel.TODO_LIST_SCROLLER_WIDTH,
                                                            TeamViewModel.TODO_LIST_SCROLLER_HEIGHT));

        // initialize the main selector panel and add everything to it
        todoSelectorPanel = new JPanel();
        todoSelectorLabel = new JLabel("Team Tasks");
        todoSelectorPanel.setLayout(new BoxLayout(todoSelectorPanel, BoxLayout.Y_AXIS));
        todoSelectorPanel.setBorder(new CompoundBorder());
        todoSelectorPanel.add(todoSelectorLabel);
        todoSelectorPanel.add(todoNameListScroller);
        todoSelectorPanel.add(todoButtonsPanel);

        /**
         * INITIALIZE TASK INFO FIELDS
         */

        todoDescriptionTextPane = new JTextPane();
        todoDescriptionTextPane.setEditable(false);
        todoDescriptionTextPane.setPreferredSize(new Dimension(TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_WIDTH,
                                                               TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_HEIGHT));
        todoDescriptionTextPane.setText("This is a test description!");

        todoStartTimeTextPane = new JEditorPane();
        todoStartTimeTextPane.setText("Start: January 5, 2024 at 12:35 PM");
        todoStartTimeTextPane.setEditable(false);
        todoEndTimeTextPane = new JEditorPane();
        todoEndTimeTextPane.setText("End: January 5, 2024 at 4:50 AM");
        todoEndTimeTextPane.setEditable(false);
        todoTimePanel = new JPanel();
        todoTimePanel.setLayout(new BoxLayout(todoTimePanel, BoxLayout.Y_AXIS));
        todoTimePanel.add(todoStartTimeTextPane);
        todoTimePanel.add(todoEndTimeTextPane);

        todoStatusTextPane = new JEditorPane();
        todoStatusTextPane.setText("Status: Incomplete");
        todoStatusTextPane.setEditable(false);
        todoAssignedTextPane = new JEditorPane();
        todoAssignedTextPane.setEditable(false);
        todoAssignedTextPane.setText("Assigned to Sean by Kyle");
        todoStatusPanel = new JPanel();
        todoStatusPanel.setBorder(new CompoundBorder());
        todoStatusPanel.setLayout(new BoxLayout(todoStatusPanel, BoxLayout.Y_AXIS));
        todoStatusPanel.add(todoStatusTextPane);
        todoStatusPanel.add(todoAssignedTextPane);

        todoInfoPanel = new JPanel();
        todoDescriptionLabel = new JLabel("Todo Description");
        todoInfoPanel.setLayout(new BoxLayout(todoInfoPanel, BoxLayout.Y_AXIS));
        todoInfoPanel.add(todoDescriptionLabel);
        todoInfoPanel.add(todoTimePanel);
        todoInfoPanel.add(todoStatusPanel);
        todoInfoPanel.add(todoDescriptionTextPane);


        /**
         * INITIALIZING TEAM SELECTION FIELDS
         */

        teamButtonsPanel = new JPanel();
        addTeamMemberButton = new JButton(TeamViewModel.ADD_TEAM_MEMBER_BUTTON_STRING);
        removeTeamMemberButton = new JButton(TeamViewModel.REMOVE_TEAM_MEMBER_BUTTON_STRING);
        teamButtonsPanel.add(addTeamMemberButton);
        teamButtonsPanel.add(removeTeamMemberButton);

        teamMembersList = new JList<>();
        teamMembersList.setListData(new String[]{"Sean", "Kyle", "Darryl"});
        teamMemberListScroller = new JScrollPane(teamMembersList);
        teamMemberListScroller.setFocusable(false);
        teamMemberListScroller.setPreferredSize(new Dimension(TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_WIDTH,
                                                              TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_HEIGHT));

        teamMemberSelectorPanel = new JPanel();
        teamSelectorLabel = new JLabel("Team Label");
        teamMemberSelectorPanel.setLayout(new BoxLayout(teamMemberSelectorPanel, BoxLayout.Y_AXIS));
        teamMemberSelectorPanel.add(teamSelectorLabel);
        teamMemberSelectorPanel.add(teamMemberListScroller);
        teamMemberSelectorPanel.add(teamButtonsPanel);

        /**
         INITIALIZING MENU FIELDS
         */

        // initialize main menu fields
        mainMenu = new JMenu(TeamViewModel.MAIN_MENU_STRING);
        logoutMainMenuItem = new JMenuItem(TeamViewModel.LOGOUT_MENU_ITEM_STRING);
        mainMenu.add(logoutMainMenuItem);

        // initialize view menu fields
        viewMenu = new JMenu(TeamViewModel.VIEW_MENU_STRING);
        personalViewMenuItem = new JMenuItem(TeamViewModel.SWITCH_TEAM_MENU_ITEM_STRING);
        viewMenu.add(personalViewMenuItem);

        // initialize weather menu fields
        weatherMenu = new JMenu(TeamViewModel.WEATHER_MENU_STRING);
        weatherViewMenuItem = new JMenuItem(TeamViewModel.WEATHER_MENU_ITEM_STRING);
        weatherMenu.add(weatherViewMenuItem);

        // initialize team menu fields
        teamMenu = new JMenu(TeamViewModel.TEAM_MENU_STRING);
        addTeamMenuItem = new JMenuItem(TeamViewModel.ADD_TEAM_MENU_ITEM_STRING);
        teamMenu.add(addTeamMenuItem);
        switchTeamSubMenu = new JMenu(TeamViewModel.SWITCH_TEAM_MENU_ITEM_STRING);
        teamMenu.add(switchTeamSubMenu);

        // initialize menu bar and add menus to it
        menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(TeamViewModel.MENU_BAR_WIDTH, TeamViewModel.MENU_BAR_HEIGHT));
        menuBar.add(mainMenu);
        menuBar.add(viewMenu);
        menuBar.add(teamMenu);
        menuBar.add(weatherMenu);

        /**
         * ADDING LISTENERS TO FIELDS
         */

        logoutMainMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to login view
            }
        });

        personalViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // switch to personal view
            }
        });

        weatherViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show pop up of weather
            }
        });

        addTeamMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // display pop up to add a new team
            }
        });

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add task
            }
        });

        removeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove task
            }
        });

        editTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // edit task
            }
        });

        completeTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // mark task done
            }
        });

        todoNameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // change selected index and update stuff
            }
        });

        // create an organization panels to add all the task and team components to
        JPanel todoAndTeamPanel = new JPanel();
        todoAndTeamPanel.setLayout(new BoxLayout(todoAndTeamPanel, BoxLayout.Y_AXIS));
        todoAndTeamPanel.add(teamMemberSelectorPanel);
        todoAndTeamPanel.add(todoSelectorPanel);

        JPanel viewPanel = new JPanel();
        viewPanel.add(todoAndTeamPanel);
        viewPanel.add(todoInfoPanel);

        // add all members to this class' parent JPanel


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(menuBar);
        this.add(viewPanel);

        //this.setPreferredSize(new Dimension(TeamViewModel.PANEL_WIDTH, TeamViewModel.PANEL_HEIGHT));
    }

}
