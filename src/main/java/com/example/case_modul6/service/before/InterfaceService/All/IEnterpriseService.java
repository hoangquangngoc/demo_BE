package com.example.case_modul6.service.before.InterfaceService.All;

import com.example.case_modul6.model.before.Enterprise;

import java.util.List;

public interface IEnterpriseService {

    void save(Enterprise enterprise);

    List<Enterprise> getAllEnterprise();

    List<Enterprise> getAllEnterpriseNotConfirmOrderByTime();


    Enterprise findEnterpriseById(int id);

    Enterprise findByGmailEnterprise(String name);


    void confirmRegisterEnterprise(String password ,int status, int id);

    void delete(int id);



//    List<Enterprise> listEnterpriseOderByRates();

//    the

//    tuan

    //    song
    void setStatusEnterpriseTo1(int id);
    void setStatusEnterpriseTo0(int id);

    void changPassword(String email, String password);

//    hai

//    duc

}
