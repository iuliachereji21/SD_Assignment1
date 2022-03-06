package org.example.presentation.controller;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.business.service.RegularUserService;
import org.example.business.service.TravellingAgencyService;
import org.example.presentation.view.MainFrame;
import org.example.presentation.view.TravellingAgencyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TravellingAgencyController {
    private final TravellingAgencyService travellingAgencyService;
    private TravellingAgency travellingAgency;
    private MainFrame mainFrame;
    private TravellingAgencyView travellingAgencyView;

    public TravellingAgencyController(MainFrame mainFrame){
        this.travellingAgencyService=new TravellingAgencyService();
        this.travellingAgency=null;
        this.mainFrame=mainFrame;
        this.travellingAgencyView=(TravellingAgencyView) mainFrame.panels[2];
        for(int i=0;i<travellingAgencyView.getNrButtons();i++)
            travellingAgencyView.addButtonListener(new TravellingAgencyController.ButtonsListenerTravellingAgency(), i);
    }
    public TravellingAgency findAgencyByEmailAndPassword(String email, String password){
        this.travellingAgency = this.travellingAgencyService.findAgencyByEmailAndPassword(email, password);

        if(this.travellingAgency != null){
            travellingAgencyView.updateTableDestinations(this.getVacationDestinations(travellingAgency));
            travellingAgencyView.updateTablePackages(this.getVacationPackages(travellingAgency));
        }

        return this.travellingAgency;
    }

    public ArrayList<VacationDestination> getVacationDestinations(TravellingAgency travellingAgency){
        return new ArrayList<>(this.travellingAgencyService.getVacationDestinations(travellingAgency));
    }

    public ArrayList<VacationPackage> getVacationPackages(TravellingAgency travellingAgency){
        return new ArrayList<>(this.travellingAgencyService.getVacationPackages(travellingAgency));
    }

    class ButtonsListenerTravellingAgency implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object event=e.getSource(); int row; travellingAgencyView.setWrongLabelVisible(false, 0,true); int result;
            if(event==travellingAgencyView.buttons.get(0)) //log out
            {

            }
            else
            if(event==travellingAgencyView.buttons.get(1)) //edit destination
            {

            }
            else
            if(event==travellingAgencyView.buttons.get(2)) //delete destination
            {

            }
            else
            if(event==travellingAgencyView.buttons.get(3)) //add destination
            {
                VacationDestination destination = travellingAgencyService.addVacationDestination(travellingAgencyView.fields.get(0).getText(),travellingAgency);
                if(destination == null)
                    travellingAgencyView.setWrongLabelVisible(true,0,false);
                travellingAgencyView.updateTableDestinations(TravellingAgencyController.this.getVacationDestinations(travellingAgency));

            }
            else
            if(event==travellingAgencyView.buttons.get(4)) //save destination
            {

            }
        }
    }

}
