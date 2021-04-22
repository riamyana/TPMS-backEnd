package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransportPackage;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportPackageRepo {

    public int addTransportPackage(TransportPackage tp);
    public int updateTransportPackage(int id, TransportPackage tp);
    public int deleteTransportPackage(int id);

}
