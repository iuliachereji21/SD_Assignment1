package org.example.presentation.controller;

import org.example.business.model.RegularUser;
import org.example.business.model.VacationPackage;
import org.example.business.service.RegularUserService;
import org.example.presentation.view.MainFrame;
import org.example.presentation.view.RegularUserView;

import java.util.ArrayList;

public class RegularUserController {
    private final RegularUserService regularUserService;
    private RegularUser user;
    private MainFrame mainFrame;
    private RegularUserView regularUserView;

    public RegularUserController(){
        this.regularUserService=new RegularUserService();
        this.user=null;
    }

    public void addMainFrame(MainFrame mainFrame){
        this.mainFrame=mainFrame;
        this.regularUserView=(RegularUserView) mainFrame.getPanel(3);
    }

    public RegularUser findUserByEmailAndPassword(String email, String password){
        this.user = this.regularUserService.findUserByEmailAndPassword(email, password);
        if(user!=null){
            regularUserView.updateTableAvailabePackages(this.getAvailablePackages());
            regularUserView.updateTableBookedPackages(new ArrayList<>(user.getBookedVacationPackages()));
        }
        return this.user;
    }

    public ArrayList<VacationPackage> getAvailablePackages(){
        return new ArrayList<>(this.regularUserService.getAvailableVacationPackages());
    }

    public void addRegularUser(String name, String email, String password, String repeatedPassword) throws Exception{
        this.user = regularUserService.addRegularUser(name, email, password, repeatedPassword);
        if(user!=null){
            regularUserView.updateTableAvailabePackages(this.getAvailablePackages());
            regularUserView.updateTableBookedPackages(new ArrayList<>(user.getBookedVacationPackages()));
            mainFrame.setPanel(3);
        }
    }

    public void logOut(){
        this.user=null;
        mainFrame.setPanel(0);
    }

    public void bookPackage(long packageId){
        VacationPackage vacationPackage = regularUserService.getPackageById(packageId);
        if(vacationPackage!=null){
            try{
                regularUserService.bookPackage(user, vacationPackage);
                regularUserView.updateTableAvailabePackages(this.getAvailablePackages());
                regularUserView.updateTableBookedPackages(new ArrayList<>(user.getBookedVacationPackages()));
            }
            catch (Exception e){
                if(e.getMessage().equals("already booked"))
                    regularUserView.setWrongLabelVisible(true,1, false);
            }

        }
    }

    public void filterAvailablePackages(String destination, String price, String startDate, String endDate){
        try{
            regularUserView.updateTableAvailabePackages(regularUserService.filterAvailablePackages(destination,price, startDate,endDate));
        }
        catch (Exception e){
            if(e.getMessage().equals("price"))
                regularUserView.setWrongLabelVisible(true,3, false);
            if(e.getMessage().equals("start date"))
                regularUserView.setWrongLabelVisible(true,4, false);
            if(e.getMessage().equals("end date"))
                regularUserView.setWrongLabelVisible(true,5, false);
        }
    }

    public void getAllPackages(){
        regularUserView.updateTableAvailabePackages(this.getAvailablePackages());
    }
}
