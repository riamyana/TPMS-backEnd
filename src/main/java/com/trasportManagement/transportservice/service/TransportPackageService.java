package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.repository.TransportPackageRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransportPackageService {

    @Autowired
    @Qualifier("transportPackageRepo")
    TransportPackageRepo transportPackageRopo;

    public Result<TransportPackage> addTransportPackage(TransportPackage tp) {
        int id = transportPackageRopo.addTransportPackage(tp);
        if(id == 0){
            return new Result<>(400, "Error in adding transport package details.");
        }else{
            tp.setId(id);
            // Code : 201 for Insert (POST)
            return new Result<>(201, tp);
        }
    }

    public Result<TransportPackage> updateTransportPackage(int id, TransportPackage p) {
        int rows = transportPackageRopo.updateTransportPackage(id,p);
        if(rows > 0){
            return new Result<>(200, p);
        }
        else{
            return new Result<>(400, "Unable to update. Given transport package id : " + p.getId()   + " not found.");
        }
    }

    public Result<TransportPackage> deleteTransportPackage(int id) {
        int rows = transportPackageRopo.deleteTransportPackage(id);
        if(rows > 0){
            return new Result<>(200, "Transport package with id : " + id + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given transport package id : " + id   + " not found.");
        }
    }
}
