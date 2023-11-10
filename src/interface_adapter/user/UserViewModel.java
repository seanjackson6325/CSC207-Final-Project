package interface_adapter.user;

import view.ViewManager;
import view.ViewModel;

public class UserViewModel extends ViewModel {

    public UserViewModel(ViewManager viewManager)
    {
        super("Personal", viewManager);
    }

}
