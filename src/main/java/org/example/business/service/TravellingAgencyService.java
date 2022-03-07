package org.example.business.service;

import org.apache.commons.validator.GenericValidator;
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

    public VacationPackage addVacationPackage(String name, String startDate, String endDate, String nrPeople, String price, String details, long destinationId, TravellingAgency travellingAgency) throws Exception{
        if(name==null || name.equals("") || name.length()>100)
            throw new Exception("name");
        if(startDate==null || startDate.equals("") ||!GenericValidator.isDate(startDate,"dd.MM.yyyy",true))
            throw new Exception("startDate");
        if(endDate==null || endDate.equals("") ||!GenericValidator.isDate(endDate,"dd.MM.yyyy",true) || endDate.compareTo(startDate)<=0)
            throw new Exception("endDate");
        if(nrPeople==null || nrPeople.equals(""))
            throw new Exception("nrPeople");

        int nrP;
        try {
            nrP = Integer.parseInt(nrPeople);
        }
        catch (Exception e){
            throw new Exception("nrPeople");
        }
        if(nrP<=0) throw new Exception("nrPeople");

        float pr;
        try {
            pr = Float.parseFloat(price);
        }
        catch (Exception e){
            throw new Exception("price");
        }
        if(pr<=0) throw new Exception("price");

        if(details==null || details.equals("") || details.length()>200)
            throw new Exception("details");

        VacationDestination vacationDestination = travellingAgencyRepository.getDestinationById(destinationId);

        VacationPackage newPackage = new VacationPackage(name, pr, startDate, endDate, details, nrP, travellingAgency, vacationDestination);
        travellingAgencyRepository.addVacationPackage(newPackage);
        return newPackage;
    }

    public void updateVacationPackage(String name, String startDate, String endDate, String nrPeople, String price, String details, long packageId, TravellingAgency travellingAgency) throws Exception{
        if(name==null || name.equals("") || name.length()>100)
            throw new Exception("name");
        if(startDate==null || startDate.equals("") ||!GenericValidator.isDate(startDate,"dd.MM.yyyy",true))
            throw new Exception("startDate");
        if(endDate==null || endDate.equals("") ||!GenericValidator.isDate(endDate,"dd.MM.yyyy",true) || endDate.compareTo(startDate)<=0)
            throw new Exception("endDate");
        if(nrPeople==null || nrPeople.equals(""))
            throw new Exception("nrPeople");

        int nrP;
        try {
            nrP = Integer.parseInt(nrPeople);
        }
        catch (Exception e){
            throw new Exception("nrPeople");
        }
        if(nrP<=0) throw new Exception("nrPeople");

        float pr;
        try {
            pr = Float.parseFloat(price);
        }
        catch (Exception e){
            throw new Exception("price");
        }
        if(pr<=0) throw new Exception("price");

        if(details==null || details.equals("") || details.length()>200)
            throw new Exception("details");

        travellingAgencyRepository.updateVacationPackage(name, startDate, endDate, nrP, pr, details, packageId);
    }

    public void deleteVacationPackageById(long package_id){
        travellingAgencyRepository.deleteVacationPackageById(package_id);
    }
    public void deleteVacationDestinationById(long destination_id){
        travellingAgencyRepository.deleteVacationDestinationById(destination_id);
    }
}

