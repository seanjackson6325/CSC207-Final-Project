package view.team;

import entity.Team;
import interface_adapter.createTeam.CreateTeamController;
import interface_adapter.createTeam.CreateTeamPresenter;
import interface_adapter.createTeam.TeamState;
import interface_adapter.createTeam.TeamViewModel;
import use_case.CreateTeam.CreateTeamInputData;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

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

    /**
     * View Models and Controllers
     */

    TeamViewModel teamViewModel;
    CreateTeamController createTeamController;

    /**
     * States
     */

    TeamState teamState;

    /**
     * Input Views
     */

    AddTodoInputView addTodoInputView;
    AddTeamInputView addTeamInputView;


    public TeamView(TeamViewModel teamViewModel, CreateTeamController createTeamController)
    {
        /**
         * INITIALIZE VIEW MODELS AND CONTROLLERS
         */
        this.teamViewModel = teamViewModel;
        this.createTeamController = createTeamController;

        /**
         * INITIALIZE TEAM STATES
         */
        teamState = new TeamState();

        /**
         * INITIALIZE INPUT VIEWS
         */

        addTodoInputView = null;
        addTeamInputView = null;

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

        // initialize text pane for description
        todoDescriptionTextPane = new JTextPane();
        todoDescriptionTextPane.setEditable(false);
        todoDescriptionTextPane.setPreferredSize(new Dimension(TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_WIDTH,
                                                               TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_HEIGHT));
        todoDescriptionTextPane.setText("This is a test description!");

        // initialize time info and panel for info
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

        // initialize status and assigned info elements
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

        // initialize main panel for info and add all components
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

        // intialize team buttons panel and buttons
        teamButtonsPanel = new JPanel();
        addTeamMemberButton = new JButton(TeamViewModel.ADD_TEAM_MEMBER_BUTTON_STRING);
        removeTeamMemberButton = new JButton(TeamViewModel.REMOVE_TEAM_MEMBER_BUTTON_STRING);
        teamButtonsPanel.add(addTeamMemberButton);
        teamButtonsPanel.add(removeTeamMemberButton);

        // initialize the scrollable team member list
        teamMembersList = new JList<>();
        teamMembersList.setListData(new String[]{"Sean", "Kyle", "Darryl"});
        teamMemberListScroller = new JScrollPane(teamMembersList);
        teamMemberListScroller.setFocusable(false);
        teamMemberListScroller.setPreferredSize(new Dimension(TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_WIDTH,
                                                              TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_HEIGHT));

        // add all components to member selector panel
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
        personalViewMenuItem = new JMenuItem(TeamViewModel.PERSONAL_VIEW_MENU_ITEM_STRING);
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
                teamViewModel.getViewManager().switchToView("Login");
            }
        });

        personalViewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamViewModel.getViewManager().switchToLastView();
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

                if(addTeamInputView != null)
                {
                    addTeamInputView = new AddTeamInputView("Enter Team Name");
                    createTeamController.execute(teamState.getAddTeamUsernameInput());
                }
            }
        });

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addTodoInputView == null)
                {
                    addTodoInputView = new AddTodoInputView("Enter Todo Attributes");
                }
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

        teamMembersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // select this current item
                // update stuff
            }
        });

        addTeamMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add new member
            }
        });

        removeTeamMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // remove team member
            }
        });

        /**
         * ADDING ELEMENTS TO PARENT PANEL
         */

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
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class AddTodoInputView
    {
        private DateTimeInputPanel startTimePanel;
        private DateTimeInputPanel endTimePanel;

        private JPanel namePanel;
        private JTextField nameField;
        private JLabel nameLabel;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        JPanel todoDescriptionPanel;
        JEditorPane todoDescriptionEditor;

        public AddTodoInputView(String title) {
            startTimePanel = new DateTimeInputPanel(null);
            endTimePanel = new DateTimeInputPanel(null);

            namePanel = new JPanel();
            nameField = new JTextField(15);
            nameLabel = new JLabel("Name: ");
            namePanel.add(nameLabel);
            namePanel.add(nameField);

            confirmButtonPanel = new JPanel();
            confirm = new JButton("Confirm");
            cancel = new JButton("Cancel");
            confirmButtonPanel.add(confirm);
            confirmButtonPanel.add(cancel);

            todoDescriptionEditor = new JEditorPane();
            todoDescriptionEditor.setPreferredSize(new Dimension(240, 200));
            todoDescriptionEditor.setMaximumSize(new Dimension(240, 200));
            todoDescriptionPanel = new JPanel();
            todoDescriptionPanel.setLayout(new BoxLayout(todoDescriptionPanel, BoxLayout.Y_AXIS));
            todoDescriptionPanel.add(new JLabel("Description"));
            todoDescriptionPanel.add(todoDescriptionEditor);

            viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.add(namePanel);
            viewPanel.add(new JLabel("Start: "));
            viewPanel.add(startTimePanel);
            viewPanel.add(new JLabel("End: "));
            viewPanel.add(endTimePanel);
            viewPanel.add(todoDescriptionPanel);
            viewPanel.add(confirmButtonPanel);

            viewFrame = new JFrame(title);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setResizable(false);
            viewFrame.setVisible(true);

            viewFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    addTodoInputView = null;
                }
            });

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeView();
                    addTodoInputView = null;
                }
            });

            nameField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    // set state name
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        }

        public void closeView()
        {
            viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    private class AddTeamInputView
    {
        private JPanel namePanel;
        private JTextField nameField;
        private JLabel nameLabel;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        public AddTeamInputView(String title) {

            namePanel = new JPanel();
            nameField = new JTextField(15);
            nameLabel = new JLabel("Name: ");
            namePanel.add(nameLabel);
            namePanel.add(nameField);

            confirmButtonPanel = new JPanel();
            confirm = new JButton("Confirm");
            cancel = new JButton("Cancel");
            confirmButtonPanel.add(confirm);
            confirmButtonPanel.add(cancel);

            viewPanel = new JPanel();
            viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
            viewPanel.add(namePanel);
            viewPanel.add(confirmButtonPanel);

            viewFrame = new JFrame(title);
            viewFrame.setLocationRelativeTo(null);
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setResizable(false);
            viewFrame.setVisible(true);

            viewFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    addTeamInputView = null;
                }
            });

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeView();
                    addTeamInputView = null;
                }
            });

            nameField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = nameField.getText() + e.getKeyChar();
                    teamState.setAddTeamUsernameInput(text);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

        }

        public void closeView()
        {
            viewFrame.dispatchEvent(new WindowEvent(viewFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

}