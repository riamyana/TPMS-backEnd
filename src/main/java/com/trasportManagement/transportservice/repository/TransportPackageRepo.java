package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import com.trasportManagement.transportservice.model.TransportPackage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportPackageRepo {

    public int addTransportPackage(TransportPackage tp);
    public int updateTransportPackage(int id, TransportPackage tp);
    public int deleteTransportPackage(int id);
    public List<PackageByTransportDTO> findAllTransportModePackages();
}
