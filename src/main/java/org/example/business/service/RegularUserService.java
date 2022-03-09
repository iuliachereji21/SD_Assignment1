package org.example.business.service;

import org.apache.commons.validator.GenericValidator;
import org.example.business.model.RegularUser;
import org.example.business.model.TravellingAgency;
import org.example.business.model.VacationDestination;
import org.example.business.model.VacationPackage;
import org.example.persistance.RegularUserRepository;

import java.util.regex.Pattern;

public class RegularUserService {
    private final RegularUserRepository regularUserRepository;

    public RegularUserService(){
        this.regularUserRepository=new RegularUserRepository();
    }

    public RegularUser findUserByEmailAndPassword(String email, String password){
        if(email==null || email.equals("") || password==null || password.equals(""))
            return null;
        return regularUserRepository.findUserByEmailAndPassword(email,password);
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

        user.addVacationPackage(vacationPackage);
        vacationPackage.addUser(user);

        regularUserRepository.bookPackage(user, vacationPackage);

    }
}
