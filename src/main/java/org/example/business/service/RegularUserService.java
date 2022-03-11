package org.example.business.service;

import org.apache.commons.validator.GenericValidator;
import org.example.business.model.RegularUser;
import org.example.business.model.VacationPackage;
import org.example.persistance.RegularUserRepository;
import org.example.persistance.VacationPackageRepository;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegularUserService {
    private final RegularUserRepository regularUserRepository;
    private final VacationPackageRepository vacationPackageRepository;

    public RegularUserService(){
        this.regularUserRepository=new RegularUserRepository();
        this.vacationPackageRepository=new VacationPackageRepository();
    }

    public RegularUser findUserByEmailAndPassword(String email, String password){
        if(email==null || email.equals("") || password==null || password.equals(""))
            return null;
        return regularUserRepository.findUserByEmailAndPassword(email,password);
    }

    public ArrayList<VacationPackage> getAvailableVacationPackages(){
        return vacationPackageRepository.getAvailableVacationPackages();
    }

    public RegularUser addRegularUser(String name, String email, String password, String repeatedPassword) throws Exception{
        if(name==null || name.equals("") || name.length()>100)
            throw new Exception("name");

        if(email==null || email.equals("") || email.length()>100 || email.contains("@agency.com"))
            throw new Exception("email");

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!Pattern.compile(regexPattern).matcher(email).matches())
            throw new Exception("email");

        if(password==null || password.equals("") || password.length()>100)
            throw new Exception("password");
        if(repeatedPassword==null || repeatedPassword.equals("") || repeatedPassword.length()>100 || !repeatedPassword.equals(password))
            throw new Exception("repeatedPassword");

        RegularUser regularUser = regularUserRepository.findUserByEmail(email);
        if(regularUser!=null)
            throw new Exception("email exists");

        RegularUser newUser = new RegularUser(name,email, password);
        regularUserRepository.addRegularUser(newUser);
        return newUser;
    }

    public void bookPackage(RegularUser user, VacationPackage vacationPackage) throws Exception{
        for(VacationPackage vp: user.getBookedVacationPackages()){
            if (vp.getId()== vacationPackage.getId())
                throw new Exception("already booked");
        }

        if(vacationPackage.addUser(user)){
            user.addVacationPackage(vacationPackage);
            regularUserRepository.bookPackage(user, vacationPackage);
        }
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
