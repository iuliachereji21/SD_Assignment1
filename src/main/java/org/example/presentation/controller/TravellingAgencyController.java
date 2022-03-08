package org.example.presentation.controller;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.business.service.RegularUserService;
import org.example.business.service.TravellingAgencyService;
import org.example.presentation.view.LogInView;
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

    public TravellingAgencyController(){
        this.travellingAgencyService=new TravellingAgencyService();
        this.travellingAgency=null;
    }

    public void addMainFrame(MainFrame mainFrame){
        this.mainFrame=mainFrame;
        this.travellingAgencyView=(TravellingAgencyView) mainFrame.panels[2];
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

    public void logOut(){
        this.travellingAgency=null;
        mainFrame.setPanel(0);
    }

    public void addVacationDestination(String name){
        VacationDestination destination = travellingAgencyService.addVacationDestination(name, travellingAgency);

        if(destination == null)
            travellingAgencyView.setWrongLabelVisible(true,0,false);
        travellingAgencyView.updateTableDestinations(TravellingAgencyController.this.getVacationDestinations(travellingAgency));
    }

    public void deleteVacationPackage(long package_id){
        travellingAgencyService.deleteVacationPackageById(package_id);
        travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
    }

    public void addVacationPackage(String name, String startDate, String endDate, String nrPeople, String price, String details, long destinationId){
        try{
            travellingAgencyService.addVacationPackage(name, startDate, endDate, nrPeople,price,details, destinationId, travellingAgency);
            travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
        }
        catch (Exception ex){
            if(ex.getMessage().equals("name"))
                travellingAgencyView.setWrongLabelVisible(true,2,false);
            if(ex.getMessage().equals("startDate"))
                travellingAgencyView.setWrongLabelVisible(true,3,false);
            if(ex.getMessage().equals("endDate"))
                travellingAgencyView.setWrongLabelVisible(true,4,false);
            if(ex.getMessage().equals("nrPeople"))
                travellingAgencyView.setWrongLabelVisible(true,5,false);
            if(ex.getMessage().equals("price"))
                travellingAgencyView.setWrongLabelVisible(true,6,false);
            if(ex.getMessage().equals("details"))
                travellingAgencyView.setWrongLabelVisible(true,7,false);

        }
    }

    public void updateVacationPackage(String name, String startDate, String endDate, String nrPeople, String price, String details, long packageId){
        try{
            travellingAgencyService.updateVacationPackage(name, startDate, endDate, nrPeople,price,details, packageId, travellingAgency);
            travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
        }
        catch (Exception ex){
            if(ex.getMessage().equals("name"))
                travellingAgencyView.setWrongLabelVisible(true,2,false);
            if(ex.getMessage().equals("startDate"))
                travellingAgencyView.setWrongLabelVisible(true,3,false);
            if(ex.getMessage().equals("endDate"))
                travellingAgencyView.setWrongLabelVisible(true,4,false);
            if(ex.getMessage().equals("nrPeople"))
                travellingAgencyView.setWrongLabelVisible(true,5,false);
            if(ex.getMessage().equals("price"))
                travellingAgencyView.setWrongLabelVisible(true,6,false);
            if(ex.getMessage().equals("details"))
                travellingAgencyView.setWrongLabelVisible(true,7,false);

        }
    }
    public void deleteVacationDestination(long destination_id){
        travellingAgencyService.deleteVacationDestinationById(destination_id);
        travellingAgencyView.updateTableDestinations(TravellingAgencyController.this.getVacationDestinations(travellingAgency));
        travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
    }
}
