package com.example.case_modul6.service.before.impl;

import com.example.case_modul6.model.before.AppUser;
import com.example.case_modul6.model.before.Roles;
import com.example.case_modul6.repository.before.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    IAppUserRepo iAppUserRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iAppUserRepo.findByUsername(username);
        List<Roles> roles = new ArrayList<>();
        roles.add(appUser.getRoles());
        if (appUser != null) {
            return new User(appUser.getEmail(), appUser.getPassword(), roles);
        }
        return null;
    }
    public List<AppUser> getAll(){
        return (List<AppUser>) iAppUserRepo.findAll();
    }

    public AppUser findByUserName(String username){
       return  iAppUserRepo.findByUsername(username);
    }
    public AppUser save(AppUser appUser){
        return iAppUserRepo.save(appUser);
    }


}
