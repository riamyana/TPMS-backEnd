package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.repository.PackageRopo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    @Qualifier("packageRepo")
    PackageRopo packageRopo;

    public Result<Package> addPackage(Package p) {
        int id = packageRopo.addPackage(p);
        if(id == 0){
            return new Result<>(400, "Error in adding package details.");
        }else{
            p.setId(id);
            // Code : 201 for Insert (POST)
            return new Result<>(201, p);
        }
    }

    public Result<Package> updatePackage(int id, Package p) {
        int rows = packageRopo.updatePackage(id,p);
        if(rows > 0){
            return new Result<>(200, p);
        }
        else{
            return new Result<>(400, "Unable to update. Given package id : " + p.getId()   + " not found.");
        }
    }

    public Result<Package> deletePackage(int id) {
        int rows = packageRopo.deletePackage(id);
        if(rows > 0){
            return new Result<>(200, "Package with id : " + id + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given package id : " + id   + " not found.");
        }
    }

    public Result<List<Package>> findAllPackage(){
        List<Package> packages = packageRopo.findAllPackage();
        if(packages.size() > 0){
            return new Result<>(200, packages);
        }
        else{
            return new Result<>(400, "No pass found.");
        }
    }

}
