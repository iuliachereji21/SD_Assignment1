package org.example.business.service;

import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.persistance.TravellingAgencyRepository;
import org.example.presentation.view.TravellingAgencyView;

import java.util.ArrayList;
import java.util.List;

public class TravellingAgencyService {
    private final TravellingAgencyRepository travellingAgencyRepository;

    public TravellingAgencyService(){
        this.travellingAgencyRepository=new TravellingAgencyRepository();
    }

    public TravellingAgency findAgencyByEmailAndPassword(String email, String password){
        if(email==null || email.equals("") || password==null || password.equals(""))
            return null;
        return travellingAgencyRepository.findAgencyByEmailAndPassword(email,password);
    }

    public ArrayList<VacationDestination> getVacationDestinations(TravellingAgency travellingAgency){
        if(travellingAgency!=null)
            return travellingAgencyRepository.getVacationDestinations(travellingAgency.getId());
        else return null;
    }

    public ArrayList<VacationPackage> getVacationPackages(TravellingAgency travellingAgency){
        if(travellingAgency!=null)
            return travellingAgencyRepository.getVacationPackages(travellingAgency.getId());
        else return null;
    }

    public VacationDestination addVacationDestination(String name, TravellingAgency travellingAgency){
        if(name==null || name.equals("") || name.length()>100)
            return null;

        VacationDestination newDest = new VacationDestination(name, travellingAgency);
        travellingAgencyRepository.addVacationDestination(newDest);
        return newDest;
    }
}

