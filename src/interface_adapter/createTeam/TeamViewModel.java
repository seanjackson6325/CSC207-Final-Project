package interface_adapter.createTeam;

import app.EntityMemory;
import entity.Todo;
import entity.User;
import interface_adapter.ViewHelper;
import view.DateTimeInputPanel;
import view.ViewManager;
import view.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TeamViewModel extends ViewModel {

    /**
     * FOR TEAM VIEW PARENT PANEL
     */
    public static final int PANEL_WIDTH = 600;
    public static final int PANEL_HEIGHT = 320;

    /**
     * FOR MENU BAR
     */
    public static final int MENU_BAR_WIDTH = PANEL_WIDTH;
    public static final int MENU_BAR_HEIGHT = 30;
    public static final String MAIN_MENU_STRING = "Menu";
    public static final String LOGOUT_MENU_ITEM_STRING = "Logout";
    public static final String REFRESH_MENU_ITEM_STRING = "Refresh";
    public static final String VIEW_MENU_STRING = "View";
    public static final String PERSONAL_VIEW_MENU_ITEM_STRING = "Switch to Personal View";
    public static final String WEATHER_MENU_STRING = "Weather";
    public static final String WEATHER_MENU_ITEM_STRING = "Check Weather";
    public static final String TEAM_MENU_STRING = "Team";
    public static final String ADD_TEAM_MENU_ITEM_STRING = "Add Team";
    public static final String SWITCH_TEAM_MENU_ITEM_STRING = "Switch Team";

    public static final int TODO_DESCRIPTION_TEXT_PANE_WIDTH = 240;
    public static final int TODO_DESCRIPTION_TEXT_PANE_HEIGHT = 200;

    /**
     * FOR TEAM MEMBER SELECTOR MENU
     */
    public static final int TEAM_MEMBER_LIST_SCROLLER_WIDTH = 250;
    public static final int TEAM_MEMBER_LIST_SCROLLER_HEIGHT = 120;
    public static final String ADD_TEAM_MEMBER_BUTTON_STRING = "Add";
    public static final String REMOVE_TEAM_MEMBER_BUTTON_STRING = "Remove";

    /**
     * FOR TASK SELECTOR MENU
     */
    public static final int TODO_LIST_SCROLLER_WIDTH = 250;
    public static final int TODO_LIST_SCROLLER_HEIGHT = 120;
    public static final String ADD_TODO_BUTTON_STRING = "Add";
    public static final String REMOVE_TODO_BUTTON_STRING = "Remove";
    public static final String EDIT_TODO_BUTTON_STRING = "Edit";
    public static final String COMPLETE_TODO_BUTTON = "Mark Done";

    /**
     instance fields
     */

    private ViewManager viewManager;
    private TeamState teamState;

    /**
     * Bean objects that are directly connected to this class' data.
     * Used in view
     */

    // FOR THE MENU BAR
    private JMenuBar menuBar;
    private JMenu switchTeamSubMenu;

    // FOR THE TEAM MENU
    private ArrayList<JMenuItem> teams;
    private ArrayList<String> teamNames;
    private int selectedTeamIndex;

    // FOR THE TEAM MEMBER MENU
    private List<String> teamMembers;
    private JList<String> teamMembersList;
    private int selectedTeamMemberIndex;

    // FOR THE TEAM TASK MENU
    private JList<String> todoNameList;
    private List<String> todoNames;
    private int selectedTodoIndex;

    // FOR THE TASK DESCRIPTION MENU
    JTextPane todoDescriptionTextPane;
    JEditorPane todoStartTimeTextPane;
    JEditorPane todoEndTimeTextPane;
    JEditorPane todoStatusTextPane;
    JEditorPane todoAssignedTextPane;


    public TeamViewModel(ViewManager viewManager)
    {
        super("Team", viewManager);
        teamState = new TeamState();

        menuBar = new JMenuBar();
        menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(TeamViewModel.MENU_BAR_WIDTH, TeamViewModel.MENU_BAR_HEIGHT));

        teamMembersList = new JList<>();
        todoNameList = new JList<>();

        switchTeamSubMenu = new JMenu(TeamViewModel.SWITCH_TEAM_MENU_ITEM_STRING);
        selectedTeamIndex = -1;
        selectedTeamMemberIndex = -1;
        selectedTodoIndex = -1;

        todoDescriptionTextPane = new JTextPane();
        todoStartTimeTextPane = new JEditorPane();
        todoEndTimeTextPane = new JEditorPane();
        todoStatusTextPane = new JEditorPane();
        todoAssignedTextPane = new JEditorPane();
    }

    public TeamState getTeamState()
    {
        return teamState;
    }


    public void updateViewData()
    {
        if(EntityMemory.getLoggedInUser() != null)
        {
            teams = new ArrayList<>();
            teamNames = new ArrayList<>();
            todoNames = new ArrayList<>();
            switchTeamSubMenu.removeAll();
            for(String team : EntityMemory.getLoggedInUser().getTeam())
            {

                teams.add(new JMenuItem(team));
                teamNames.add(team);
                switchTeamSubMenu.add(teams.get(teams.size() - 1));

                teams.get(teams.size() - 1).addActionListener(new ActionListener() {

                    private int i = teams.size() - 1;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedTeamIndex = i;
                        updateViewData();
                    }
                });
            }

            if(selectedTeamIndex != -1)
            {
                selectedTodoIndex = todoNameList.getSelectedIndex();
                String selectedTeamName = teamNames.get(selectedTeamIndex);
                teamMembers = ViewHelper.getTeamByName(selectedTeamName).getMembers();
                String[] teamMemberJListInput = new String[teamMembers.size()];
                for(int i = 0; i < teamMemberJListInput.length; i++)
                {
                    teamMemberJListInput[i] = teamMembers.get(i);
                }
                teamMembersList.setListData(teamMemberJListInput);

                List<Todo> todos = ViewHelper.getTeamByName(selectedTeamName).getTeamTasks();
                String[] teamTodoJListInput = new String[todos.size()];
                for(int i = 0; i < teamTodoJListInput.length; i++)
                {
                    teamTodoJListInput[i] = todos.get(i).getName();
                }
                todoNameList.setListData(teamTodoJListInput);

                updateDescriptionData(selectedTeamName);
            }
        }
    }

    public void updateDescriptionData(String selectedTeamName)
    {
        if(selectedTodoIndex != -1)
        {
            Todo selectedTodo = ViewHelper.getTeamByName(selectedTeamName).getTeamTasks().get(selectedTodoIndex);
            todoDescriptionTextPane.setText(selectedTodo.getDescription());
            todoStartTimeTextPane.setText(DateTimeInputPanel.getFormattedTimeString(selectedTodo.getStartTime()));
            todoEndTimeTextPane.setText(DateTimeInputPanel.getFormattedTimeString(selectedTodo.getEndTime()));
            boolean status = selectedTodo.getStatus();
            String statusString = "Incomplete";
            if(status)
                statusString = "Complete";
            todoStatusTextPane.setText(statusString);
            todoAssignedTextPane.setText("Assigned to " + selectedTodo.getRequestedTo() + " by " + selectedTodo.getRequester());
        }
    }

    public JMenuBar getMenuBar()
    {
        return menuBar;
    }

    public JMenu getSwitchTeamSubMenu()
    {
        return switchTeamSubMenu;
    }

    public JList<String> getTeamMembersList()
    {
        return teamMembersList;
    }

    public ArrayList<String> getTeamNames()
    {
        return teamNames;
    }

    public int getSelectedTeamIndex()
    {
        return selectedTeamIndex;
    }

    public String getSelectedTeamName()
    {
        return teamNames.get(selectedTeamIndex);
    }

    public List<String> getTeamMembers()
    {
        return teamMembers;
    }


    public int getSelectedTeamMemberIndex()
    {
        return selectedTeamMemberIndex;
    }

    public void setSelectedTeamMemberIndex(int index)
    {
        selectedTeamMemberIndex = index;
    }

    public String getSelectedTeamMemberName()
    {
        return teamMembers.get(selectedTeamMemberIndex);
    }

    public JList<String> getTodoNameList()
    {
        return todoNameList;
    }

    public JTextPane getTodoDescriptionTextPane()
    {
        return todoDescriptionTextPane;
    }

    public JEditorPane getTodoStartTimeTextPane()
    {
        return todoStartTimeTextPane;
    }

    public JEditorPane getTodoEndTimeTextPane()
    {
        return todoEndTimeTextPane;
    }

    public JEditorPane getTodoStatusTextPane()
    {
        return todoStatusTextPane;
    }

    public JEditorPane getTodoAssignedTextPane()
    {
        return todoAssignedTextPane;
    }

    public int getSelectedTodoIndex()
    {
        return selectedTodoIndex;
    }

    public void setSelectedTodoIndex(int index)
    {
        selectedTodoIndex = index;
    }

    public Todo getSelectedTodo()
    {
        return ViewHelper.getTeamByName(getSelectedTeamName()).getTeamTasks().get(selectedTodoIndex);
    }

}
