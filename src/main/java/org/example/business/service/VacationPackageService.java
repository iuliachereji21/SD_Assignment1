package org.example.business.service;

import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.persistance.VacationPackageRepository;

import java.util.ArrayList;

public class VacationPackageService {
    private final VacationPackageRepository vacationPackageRepository;

    public VacationPackageService(){
        this.vacationPackageRepository=new VacationPackageRepository();
    }

    public ArrayList<VacationPackage> getAvailableVacationPackages(){
        return vacationPackageRepository.getAvailableVacationPackages();
    }

    public ArrayList<VacationPackage> getBookedPackages(RegularUser user){
        if(user!=null)
            return vacationPackageRepository.getBookedPackagesByUserId(user.getId());
        else return null;
    }
}
