package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PackageDTO;
import com.trasportManagement.transportservice.model.SubscriptionType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRopo {
    public int addPackage(Package p);
    public int updatePackage(int id, Package p);
    public boolean deletePackage(int id);
    public List<PackageDTO> findAllPackage();
    public List<Package> findPackageById(int id);
    public List<SubscriptionType> findAllSubscriptionType();
    public List<Package> findPackageBySubTypeId(String subType);
    public Optional<Integer> findValidityById(int id);
}
