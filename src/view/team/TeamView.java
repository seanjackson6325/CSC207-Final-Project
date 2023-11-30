package view.team;

import app.EntityMemory;
import entity.Team;
import interface_adapter.addMember.AddMemberController;
import interface_adapter.checkWeather.CheckWeatherController;
import interface_adapter.createTeam.CreateTeamController;
import interface_adapter.createTeam.CreateTeamPresenter;
import interface_adapter.createTeam.TeamState;
import interface_adapter.createTeam.TeamViewModel;
import interface_adapter.createTodoTeam.CreateTodoTeamController;
import interface_adapter.removeMember.RemoveMemberController;
import use_case.CreateTeam.CreateTeamInputData;
import view.DateTimeInputPanel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TeamView extends JPanel {

    /**
     *  FOR THE TOOLBAR
     */
    JMenu mainMenu, viewMenu, weatherMenu, teamMenu;
    JMenuItem logoutMainMenuItem, refreshMainMenuItem, personalViewMenuItem, weatherViewMenuItem, addTeamMenuItem;

    /**
     *  FOR THE TASK SELECTION MENU
     * */
    JLabel todoSelectorLabel;
    JPanel todoSelectorPanel;
    JPanel todoButtonsPanel;
    JButton addTodoButton, removeTodoButton, editTodoButton, completeTodoButton;
    JScrollPane todoNameListScroller;

    /**
     * FOR THE TASK DESCRIPTION MENU
     */
    JLabel todoDescriptionLabel;
    JPanel todoInfoPanel;
    JPanel todoTimePanel;
    JPanel todoStatusPanel;

    /**
     * FOR THE TEAM MENU
     */

    JLabel teamSelectorLabel;
    JPanel teamMemberSelectorPanel;
    JScrollPane teamMemberListScroller;
    JPanel teamButtonsPanel;
    JButton addTeamMemberButton;
    JButton removeTeamMemberButton;

    /**
     * View Models and Controllers
     */

    TeamViewModel teamViewModel;
    CreateTeamController createTeamController;
    AddMemberController addMemberController;
    RemoveMemberController removeMemberController;
    CreateTodoTeamController createTodoTeamController;
    CheckWeatherController checkWeatherController;

    /**
     * States
     */

    TeamState teamState;

    /**
     * Input Views
     */

    AddTodoInputView addTodoInputView;
    AddTeamInputView addTeamInputView;
    AddTeamMemberInputView addTeamMemberInputView;


    public TeamView(TeamViewModel teamViewModel,
                    CreateTeamController createTeamController,
                    AddMemberController addMemberController,
                    RemoveMemberController removeMemberController,
                    CreateTodoTeamController createTodoTeamController,
                    CheckWeatherController checkWeatherController)
    {
        /**
         * INITIALIZE VIEW MODELS AND CONTROLLERS
         */
        this.teamViewModel = teamViewModel;
        this.createTeamController = createTeamController;
        this.addMemberController = addMemberController;
        this.removeMemberController = removeMemberController;
        this.createTodoTeamController = createTodoTeamController;
        this.checkWeatherController = checkWeatherController;

        /**
         * INITIALIZE TEAM STATES
         */
        teamState = new TeamState();

        /**
         * INITIALIZE INPUT VIEWS
         */

        addTodoInputView = null;
        addTeamInputView = null;
        addTeamMemberInputView = null;

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
        todoNameListScroller = new JScrollPane(teamViewModel.getTodoNameList());
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
        teamViewModel.getTodoDescriptionTextPane().setEditable(false);
        teamViewModel.getTodoDescriptionTextPane().setPreferredSize(new Dimension(TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_WIDTH,
                                                               TeamViewModel.TODO_DESCRIPTION_TEXT_PANE_HEIGHT));

        // initialize time info and panel for info
        teamViewModel.getTodoStartTimeTextPane().setEditable(false);
        teamViewModel.getTodoEndTimeTextPane().setEditable(false);
        todoTimePanel = new JPanel();
        todoTimePanel.setLayout(new BoxLayout(todoTimePanel, BoxLayout.Y_AXIS));
        todoTimePanel.add(teamViewModel.getTodoStartTimeTextPane());
        todoTimePanel.add(teamViewModel.getTodoEndTimeTextPane());

        // initialize status and assigned info elements
        teamViewModel.getTodoStatusTextPane().setEditable(false);
        teamViewModel.getTodoAssignedTextPane().setEditable(false);
        todoStatusPanel = new JPanel();
        todoStatusPanel.setBorder(new CompoundBorder());
        todoStatusPanel.setLayout(new BoxLayout(todoStatusPanel, BoxLayout.Y_AXIS));
        todoStatusPanel.add(teamViewModel.getTodoStatusTextPane());
        todoStatusPanel.add(teamViewModel.getTodoAssignedTextPane());

        // initialize main panel for info and add all components
        todoInfoPanel = new JPanel();
        todoDescriptionLabel = new JLabel("Todo Description");
        todoInfoPanel.setLayout(new BoxLayout(todoInfoPanel, BoxLayout.Y_AXIS));
        todoInfoPanel.add(todoDescriptionLabel);
        todoInfoPanel.add(todoTimePanel);
        todoInfoPanel.add(todoStatusPanel);
        todoInfoPanel.add(teamViewModel.getTodoDescriptionTextPane());


        /**
         * INITIALIZING TEAM MEMBER SELECTION FIELDS
         */

        // intialize team buttons panel and buttons
        teamButtonsPanel = new JPanel();
        addTeamMemberButton = new JButton(TeamViewModel.ADD_TEAM_MEMBER_BUTTON_STRING);
        removeTeamMemberButton = new JButton(TeamViewModel.REMOVE_TEAM_MEMBER_BUTTON_STRING);
        teamButtonsPanel.add(addTeamMemberButton);
        teamButtonsPanel.add(removeTeamMemberButton);

        // initialize the scrollable team member list
        teamMemberListScroller = new JScrollPane(teamViewModel.getTeamMembersList());
        teamMemberListScroller.setFocusable(false);
        teamMemberListScroller.setPreferredSize(new Dimension(TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_WIDTH,
                                                              TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_HEIGHT));


        // add all components to member selector panel
        teamMemberSelectorPanel = new JPanel();
        teamSelectorLabel = new JLabel("Team Members");
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
        refreshMainMenuItem = new JMenuItem(TeamViewModel.REFRESH_MENU_ITEM_STRING);
        mainMenu.add(refreshMainMenuItem);

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
        teamMenu.add(teamViewModel.getSwitchTeamSubMenu());

        // initialize menu bar and add menus to it
        JMenuBar menuBar = teamViewModel.getMenuBar();
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

        refreshMainMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamViewModel.updateViewData();
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
                checkWeatherController.execute();
            }
        });

        addTeamMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(addTeamInputView == null)
                {
                    addTeamInputView = new AddTeamInputView("Enter Team Name");
                }
            }
        });

        addTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addTodoInputView == null && teamViewModel.getSelectedTeamIndex() != -1)
                {
                    addTodoInputView = new AddTodoInputView("Enter Todo Attributes");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a team first.");
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

        teamViewModel.getTodoNameList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                teamViewModel.setSelectedTodoIndex(teamViewModel.getTodoNameList().getSelectedIndex());
                teamViewModel.updateDescriptionData(teamViewModel.getSelectedTeamName());
            }
        });

        teamViewModel.getTeamMembersList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = teamViewModel.getTeamMembersList().getSelectedIndex();
                teamViewModel.setSelectedTeamMemberIndex(index);
            }
        });

        addTeamMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addTeamMemberInputView == null && teamViewModel.getSelectedTeamIndex() != -1)
                {
                    addTeamMemberInputView = new AddTeamMemberInputView("Input Username");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a team first.");
                }
            }
        });

        removeTeamMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teamViewModel.getSelectedTeamMemberIndex() != -1)
                {
                    String teamMemberName = teamViewModel.getSelectedTeamMemberName();
                    String teamName = teamViewModel.getSelectedTeamName();
                    String message = "Remove " + teamMemberName + " from team " + teamName + "?";
                    int option = JOptionPane.showConfirmDialog(null, message);
                    if(option == 0)
                    {
                        removeMemberController.execute(
                                teamViewModel.getSelectedTeamMemberName(),
                                teamViewModel.getSelectedTeamName()
                        );
                        teamViewModel.updateViewData();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please select a team first.");
                }
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
        private JLabel assignedToLabel;
        private JList<String> membersList;
        private JScrollPane scroller;
        int selectedMemberIndex;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        JPanel todoDescriptionPanel;
        JEditorPane todoDescriptionEditor;

        public AddTodoInputView(String title) {
            startTimePanel = new DateTimeInputPanel(null);
            endTimePanel = new DateTimeInputPanel(null);

            selectedMemberIndex = -1;

            List<String> members = teamViewModel.getTeamMembers();
            String[] teamMemberJListInput = new String[members.size()];
            for(int i = 0; i < teamMemberJListInput.length; i++)
            {
                teamMemberJListInput[i] = members.get(i);
            }
            membersList = new JList<>(teamMemberJListInput);

            scroller = new JScrollPane(membersList);
            scroller.setFocusable(false);
            scroller.setPreferredSize(new Dimension(TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_WIDTH,
                    TeamViewModel.TEAM_MEMBER_LIST_SCROLLER_HEIGHT));

            membersList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    selectedMemberIndex = membersList.getSelectedIndex();
                }
            });


            namePanel = new JPanel();
            nameField = new JTextField(15);
            nameLabel = new JLabel("Name: ");
            assignedToLabel = new JLabel("Assign to:");
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            namePanel.add(assignedToLabel);
            namePanel.add(scroller);



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

                    if (!startTimePanel.isValidInput() || !endTimePanel.isValidInput()) {
                        JOptionPane.showMessageDialog(null, "Please finish entering valid information");
                        teamViewModel.getTeamState().setIsAddTodoFailed(true);
                        return;
                    }

                    if(selectedMemberIndex == -1)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a team member first!");
                    }


                    String user = EntityMemory.getLoggedInUser().getUsername();

                    createTodoTeamController.execute(
                            nameField.getText(),
                            todoDescriptionEditor.getText(),
                            startTimePanel.getLocalDateTime(),
                            endTimePanel.getLocalDateTime(),
                            EntityMemory.getLoggedInUser().getUsername(),
                            teamViewModel.getTeamMembers().get(selectedMemberIndex),
                            false,
                            teamViewModel.getSelectedTeamName()
                    );

                    if (!teamViewModel.getTeamState().getIsAddTodoFailed()) {
                        teamViewModel.updateViewData();
                        closeView();
                    }
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
                    createTeamController.execute(teamState.getAddTeamNameInput());

                    if(!teamViewModel.getTeamState().getIsCreateTeamFailed())
                    {
                        closeView();
                        addTeamInputView = null;
                        teamViewModel.updateViewData();
                    }
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
                    teamState.setAddTeamNameInput(text);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private class AddTeamMemberInputView
    {
        private JPanel namePanel;
        private JTextField nameField;
        private JLabel nameLabel;

        private JPanel viewPanel;
        private JFrame viewFrame;

        private JPanel confirmButtonPanel;
        private JButton confirm, cancel;

        public AddTeamMemberInputView(String title) {

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
                    addTeamMemberInputView = null;
                }
            });

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(teamViewModel.getSelectedTeamIndex() == -1)
                    {
                        JOptionPane.showMessageDialog(null, "Please select a team first");
                        return;
                    }
                    addMemberController.execute(teamState.getAddMemberNameInput(), teamViewModel.getSelectedTeamName());

                    if(!teamViewModel.getTeamState().getIsAddTeamMemberFailed())
                    {
                        closeView();
                        addTeamMemberInputView = null;
                        teamViewModel.updateViewData();
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeView();
                    addTeamMemberInputView = null;
                }
            });

            nameField.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    String text = nameField.getText() + e.getKeyChar();
                    teamState.setAddMemberNameInput(text);
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