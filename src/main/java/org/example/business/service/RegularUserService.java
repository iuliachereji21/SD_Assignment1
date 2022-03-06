package org.example.business.service;

import org.example.business.model.RegularUser;
import org.example.persistance.RegularUserRepository;

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
}
