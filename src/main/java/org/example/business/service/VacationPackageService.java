package org.example.business.service;

import org.apache.commons.validator.GenericValidator;
import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.persistance.VacationPackageRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public VacationPackage getPackageById(long id){
        return vacationPackageRepository.getVacationPackageById(id);
    }

    public ArrayList<VacationPackage> filterAvailablePackages(final String destination, String price, String startDate, String endDate) throws Exception{
        ArrayList<VacationPackage> packages = vacationPackageRepository.getAvailableVacationPackages();

        if(price!=null)
            try{
                long pr = Long.parseLong(price);
                packages = packages.stream()
                        .filter(p -> p.getPrice()==pr)
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            catch (Exception e){
                throw new Exception("price");
            }

        if(startDate!=null)
            if(!GenericValidator.isDate(startDate,"dd.MM.yyyy",true))
                throw new Exception("start date");
            else
                packages = packages.stream()
                        .filter(p -> p.getStartDate().equals(startDate))
                        .collect(Collectors.toCollection(ArrayList::new));

        if(endDate!=null)
            if(!GenericValidator.isDate(endDate,"dd.MM.yyyy",true))
                throw new Exception("end date");
            else
                packages = packages.stream()
                        .filter(p -> p.getEndDate().equals(endDate))
                        .collect(Collectors.toCollection(ArrayList::new));

        if(destination!=null)
            packages = packages.stream()
                    .filter(p -> p.getVacationDestination().getName().equals(destination))
                    .collect(Collectors.toCollection(ArrayList::new));


        return packages;
    }
}
