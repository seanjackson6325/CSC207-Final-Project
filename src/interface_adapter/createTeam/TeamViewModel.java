package interface_adapter.createTeam;

import app.EntityMemory;
import entity.User;
import view.ViewManager;
import view.ViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JMenuBar menuBar;
    private JMenu switchTeamSubMenu;
    private ArrayList<JMenuItem> teams;
    private int selectedTeamIndex;



    public TeamViewModel(ViewManager viewManager)
    {
        super("Team", viewManager);
        teamState = new TeamState();

        menuBar = new JMenuBar();
        menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(TeamViewModel.MENU_BAR_WIDTH, TeamViewModel.MENU_BAR_HEIGHT));

        switchTeamSubMenu = new JMenu(TeamViewModel.SWITCH_TEAM_MENU_ITEM_STRING);
        selectedTeamIndex = 0;
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
            switchTeamSubMenu.removeAll();
            for(String team : EntityMemory.getLoggedInUser().getTeam())
            {
                System.out.println(EntityMemory.getLoggedInUser().getTeam().size());
                System.out.println(team);

                teams.add(new JMenuItem(team));
                switchTeamSubMenu.add(teams.get(teams.size() - 1));
                teams.get(teams.size() - 1).addActionListener(new ActionListener() {

                    private int i = teams.size() - 1;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedTeamIndex = i;
                        System.out.println(selectedTeamIndex);
                    }
                });
            }
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

}
