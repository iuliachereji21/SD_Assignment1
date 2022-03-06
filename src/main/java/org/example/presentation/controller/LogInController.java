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

    public LogInController(MainFrame mainFrame, RegularUserController regularUserController, TravellingAgencyController travellingAgencyController)
    {
        this.regularUserController=regularUserController;
        this.travellingAgencyController=travellingAgencyController;
        this.mainFrame=mainFrame;
        this.logInView=(LogInView) mainFrame.panels[0];
        for(int i=0;i<logInView.getNrButtons();i++)
            logInView.addButtonListener(new ButtonsListenerLogIn(), i);
    }

    class ButtonsListenerLogIn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; logInView.setWrongLabelVisible(false, 0,true); int result;
            if(event==logInView.buttons.get(0)) //log in
            {
                String email = logInView.fields.get(0).getText();
                String password = logInView.fields.get(1).getText();

                if(email.contains("@agency.com")){
                    TravellingAgency agency = travellingAgencyController.findAgencyByEmailAndPassword(email, password);
                    if(agency==null)
                        logInView.setWrongLabelVisible(true,0,false);
                    else mainFrame.setPanel(2);
                }
                else{
                    RegularUser user = regularUserController.findUserByEmailAndPassword(email, password);
                    if(user==null)
                        logInView.setWrongLabelVisible(true,0,false);
                    else mainFrame.setPanel(3);
                }
            }
            else
                if(event==logInView.buttons.get(1)) //register
                {
                    mainFrame.setPanel(1);
                }
        }
    }
}
