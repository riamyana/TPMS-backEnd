package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PackageDTO;
import com.trasportManagement.transportservice.model.SubscriptionType;
import com.trasportManagement.transportservice.repository.PackageRopo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    @Qualifier("packageRepo")
    PackageRopo packageRopo;

    public Package addPackage(Package p) {
        int n = packageRopo.addPackage(p);
        p.setId(n);

        if (n == 0) {
            throw new TPMSCustomException("No record inserted of this package.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return p;
    }

    public Package updatePackage(int id, Package p) {
        int n = packageRopo.updatePackage(id, p);
        p.setId(id);

        if(n == 0){
            throw  new TPMSCustomException("Unable to update. Given package id : " + p.getId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deletePackage(int id) {
        if(!packageRopo.deletePackage(id)){
            throw new TPMSCustomException("Unable to delete. Given package id : " + id   + " not found.", HttpStatus.NOT_FOUND);
        }

        return true;
    }

    public List<PackageDTO> findAllPackage(){
        List<PackageDTO> packages = packageRopo.findAllPackage();
        if(packages.isEmpty()){
            throw  new TPMSCustomException("No packages found", HttpStatus.NOT_FOUND);
        }

        return packages;
    }

    public List<Package> findPackageById(int id){
        List<Package> packages = packageRopo.findPackageById(id);
        if(packages.isEmpty()){
            throw  new TPMSCustomException("No package found", HttpStatus.NOT_FOUND);
        }

        return packages;
    }

    public List<SubscriptionType> findAllSubscriptionType(){
        List<SubscriptionType> types = packageRopo.findAllSubscriptionType();

        if(types.isEmpty()){
            throw  new TPMSCustomException("No subscription type found", HttpStatus.NOT_FOUND);
        }

        return types;
    }

    public List<Package> findPackageBySubTypeId(String subType){
        List<Package> packages = packageRopo.findPackageBySubTypeId(subType);

        if(packages.isEmpty()){
            throw  new TPMSCustomException("No package found for subscription id: "+subType, HttpStatus.NOT_FOUND);
        }
        return packages;
    }

    public int findValidityById(int id){

        return packageRopo.findValidityById(id)
                .orElseThrow(() ->
                        new TPMSCustomException("No validity found for id:"+id, HttpStatus.NOT_FOUND));

    }

}
