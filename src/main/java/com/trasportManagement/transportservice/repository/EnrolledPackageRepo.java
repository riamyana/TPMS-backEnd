package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.EnrolledMemberPackage;
import com.trasportManagement.transportservice.model.EnrolledPackage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolledPackageRepo {
    public int addEnrolledPackage(EnrolledPackage e);
    int updateIsActive(EnrolledPackage e);
    //    public int updateEnrolledPackage(int id, EnrolledPackage e);
//    public int deleteEnrolledPackage(int id);
    public List<EnrolledPackage> findAllEnrolledPackage();
    public List<EnrolledPackage> findEnrolledPackageByPassId(int passId);

    List<EnrolledMemberPackage> findEnrolledPkgByPassId(int passId);
}
