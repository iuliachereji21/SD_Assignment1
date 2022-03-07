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
            if(event==travellingAgencyView.buttons.get(2)) //delete destination
            {
                if((row=travellingAgencyView.destinationsTable.getSelectedRow())>=0){
                    long destination_id = (long)travellingAgencyView.destinationsTable.getValueAt(row,0);
                    travellingAgencyService.deleteVacationDestinationById(destination_id);
                    travellingAgencyView.updateTableDestinations(TravellingAgencyController.this.getVacationDestinations(travellingAgency));
                    travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
                }
                else travellingAgencyView.setWrongLabelVisible(true,9,false);
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
            if(event==travellingAgencyView.buttons.get(5)) //edit package
            {
                if((row=travellingAgencyView.packagesTable.getSelectedRow())>=0){
                    long package_id = (long)travellingAgencyView.packagesTable.getValueAt(row,0);
                    travellingAgencyView.updateFieldsToEdit(row,true);
                }
                else travellingAgencyView.setWrongLabelVisible(true,8,false);
            }
            else
            if(event==travellingAgencyView.buttons.get(6)) //delete package
            {
                if((row=travellingAgencyView.packagesTable.getSelectedRow())>=0){
                    long package_id = (long)travellingAgencyView.packagesTable.getValueAt(row,0);
                    travellingAgencyService.deleteVacationPackageById(package_id);
                    travellingAgencyView.updateTablePackages(TravellingAgencyController.this.getVacationPackages(travellingAgency));
                }
                else travellingAgencyView.setWrongLabelVisible(true,8,false);
            }
            else
            if(event==travellingAgencyView.buttons.get(7)) //add package
            {
                if((row=travellingAgencyView.destinationsTable.getSelectedRow())>=0){
                    String name = travellingAgencyView.fields.get(1).getText();
                    String start_date = travellingAgencyView.fields.get(2).getText();
                    String end_date = travellingAgencyView.fields.get(3).getText();
                    String nr_people = travellingAgencyView.fields.get(4).getText();
                    String price = travellingAgencyView.fields.get(5).getText();
                    String details = travellingAgencyView.fields.get(6).getText();
                    long destination_id = (long)travellingAgencyView.destinationsTable.getValueAt(row,0);
                    try{
                        travellingAgencyService.addVacationPackage(name, start_date, end_date, nr_people,price,details, destination_id, travellingAgency);
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

                    //travellingAgencyView.fields.get(1).setText((String)(travellingAgencyView.destinationsTable.getValueAt(row,1)));
                    //travellingAgencyView.fields.get(7).setText((String)(travellingAgencyView.destinationsTable.getValueAt(row,0)));
                }
                else travellingAgencyView.setWrongLabelVisible(true,1,false);

            }
            else
            if(event==travellingAgencyView.buttons.get(8)) //save package
            {
                String name = travellingAgencyView.fields.get(1).getText();
                String start_date = travellingAgencyView.fields.get(2).getText();
                String end_date = travellingAgencyView.fields.get(3).getText();
                String nr_people = travellingAgencyView.fields.get(4).getText();
                String price = travellingAgencyView.fields.get(5).getText();
                String details = travellingAgencyView.fields.get(6).getText();
                long package_id = Long.parseLong(travellingAgencyView.fields.get(8).getText());
                try{
                    travellingAgencyService.updateVacationPackage(name, start_date, end_date, nr_people,price,details, package_id, travellingAgency);
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
        }
    }

}
