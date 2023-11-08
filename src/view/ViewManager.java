package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewManager {

    HashMap<String, JPanel> views;
    String lastView, currentView;

    JFrame applicationFrame;

    public ViewManager(JFrame applicationFrame)
    {
        views = new HashMap<>();
        this.applicationFrame = applicationFrame;
        lastView = null;
        currentView = null;
    }

    public void addView(JPanel view, String name)
    {
        views.put(name, view);
    }

    public void switchToView(String name)
    {
        lastView = currentView;
        currentView = name;

        if(lastView != null)
        {
            applicationFrame.remove(views.get(lastView));
        }
        if(currentView != null)
        {
            applicationFrame.add(views.get(currentView));
            applicationFrame.pack();
        }
    }

    public String getLastView()
    {
        return lastView;
    }
    public String getCurrentView()
    {
        return currentView;
    }

    public void switchToLastView()
    {
        switchToView(lastView);
    }


}