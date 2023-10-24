package app;

import interface_adapter.PlaceholderViewModel;
import view.PlaceholderView;

public class main {

    public static void main(String[] args)
    {
        PlaceholderViewModel placeHolderViewModel = new PlaceholderViewModel();
        PlaceholderView placeHolderView = new PlaceholderView(placeHolderViewModel);
    }
}
