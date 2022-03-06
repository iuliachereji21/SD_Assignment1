package org.example.presentation.controller;

import org.example.business.model.RegularUser;
import org.example.business.service.RegularUserService;

public class RegularUserController {
    private final RegularUserService regularUserService;
    private RegularUser user;

    public RegularUserController(){
        this.regularUserService=new RegularUserService();
        this.user=null;
    }
    public RegularUser findUserByEmailAndPassword(String email, String password){
        this.user = this.regularUserService.findUserByEmailAndPassword(email, password);
        return this.user;
    }
}
