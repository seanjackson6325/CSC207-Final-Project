package interface_adapter.createTeam;

import view.ViewManager;
import view.ViewModel;

public class TeamViewModel extends ViewModel {
    public TeamViewModel(String name, ViewManager viewManager) {
        super("Team", viewManager);
    }
}
