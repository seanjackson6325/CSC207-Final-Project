package view;

public abstract class ViewModel {

    public final String MODEL_NAME;
    private ViewManager viewManager;

    public ViewModel(String name, ViewManager viewManager)
    {
        this.MODEL_NAME = name;
        this.viewManager = viewManager;
    }

    public String getName()
    {
        return MODEL_NAME;
    }

    public ViewManager getViewManager()
    {
        return viewManager;
    }

    public void setViewManager(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }


}
