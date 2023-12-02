package interface_adapter.checkWeather;

import interface_adapter.createTeam.TeamViewModel;
import use_case.CheckWeather.CheckWeatherOutputBoundary;
import use_case.CheckWeather.CheckWeatherOutputData;
import view.ViewManager;

import javax.swing.*;

public class CheckWeatherPresenter implements CheckWeatherOutputBoundary {

    ViewManager viewManager;

    public CheckWeatherPresenter(ViewManager viewManager)
    {
        this.viewManager = viewManager;
    }


    @Override
    public void prepareSuccessView(CheckWeatherOutputData checkWeatherOutputData) {
        JOptionPane.showMessageDialog(null, checkWeatherOutputData.getWeather());
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
}
