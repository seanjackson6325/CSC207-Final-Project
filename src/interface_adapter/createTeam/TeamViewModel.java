package interface_adapter.createTeam;

import view.ViewManager;
import view.ViewModel;

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

    ViewManager viewManager;



    public TeamViewModel(ViewManager viewManager)
    {
        super("team", viewManager);
    }

    public ViewManager getViewManger()
    {
        return viewManager;
    }

}
