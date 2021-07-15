package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.model.TransportPackageDTO;
import com.trasportManagement.transportservice.repository.TransportPackageRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportPackageService {

    @Autowired
    @Qualifier("transportPackageRepo")
    TransportPackageRepo transportPackageRopo;

    public TransportPackage addTransportPackage(TransportPackage tp) {
        int n = transportPackageRopo.addTransportPackage(tp);
        tp.setId(n);
        if(n == 0){
            throw new TPMSCustomException("No record inserted of this Tranport Package", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return tp;
    }

    public TransportPackage updateTransportPackage(int id, TransportPackage p) {
        int n = transportPackageRopo.updateTransportPackage(id,p);
        p.setId(id);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given transport package id : " + p.getId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deleteTransportPackage(int id) {
        if(!transportPackageRopo.deleteTransportPackage(id)){
            throw new TPMSCustomException("Unable to delete. Given transport package id : " + id   + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

//    public Result<List<TransportPackageDTO>> findAllTransportModePackages() {
//        List<TransportPackageDTO> result = transportPackageRopo.findAllTransportModePackages();
//        if(result.size() > 0){
//            return new Result<>(200, result);
//        }
//        else{
//            return new Result<>(400, "No pass found.");
//        }
//    }

    public List<PackageByTransportDTO> findAllTransportModePackages() {
        List<PackageByTransportDTO> result = transportPackageRopo.findAllTransportModePackages();
        if(result.isEmpty()){
            throw  new TPMSCustomException("No package found.", HttpStatus.NOT_FOUND);
        }

        return result;
    }


}
