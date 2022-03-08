package org.example.presentation.controller;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.presentation.view.LogInView;
import org.example.presentation.view.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController {
    private MainFrame mainFrame;
    private LogInView logInView;
    private RegularUserController regularUserController;
    private TravellingAgencyController travellingAgencyController;

    public LogInController(RegularUserController regularUserController, TravellingAgencyController travellingAgencyController)
    {
        this.regularUserController=regularUserController;
        this.travellingAgencyController=travellingAgencyController;

    }

    public void addMainFrame(MainFrame mainFrame){
        this.mainFrame=mainFrame;
        this.logInView=(LogInView) mainFrame.panels[0];
    }

    public void logInAgency(String email, String password){
        TravellingAgency agency = travellingAgencyController.findAgencyByEmailAndPassword(email, password);
        if(agency==null)
            logInView.setWrongLabelVisible(true,0,false);
        else mainFrame.setPanel(2);
    }

    public void logInRegularUser(String email, String password){
        RegularUser user = regularUserController.findUserByEmailAndPassword(email, password);
        if(user==null)
            logInView.setWrongLabelVisible(true,0,false);
        else mainFrame.setPanel(3);
    }

}
