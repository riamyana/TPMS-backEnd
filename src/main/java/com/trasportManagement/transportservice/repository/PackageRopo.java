package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.SubscriptionType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRopo {
    public int addPackage(Package p);
    public int updatePackage(int id, Package p);
    public int deletePackage(int id);
    public List<Package> findAllPackage();
    public List<Package> findPackageById(int id);
    public List<SubscriptionType> findAllSubscriptionType();
}
