package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypePackageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTypePackageRepo {
    public int addMemberTypePackage(MemberTypePackage mp);
    public int updateMemberTypePackage(int id, MemberTypePackage mp);
    public boolean deleteMemberTypePackage(int id);
    public List<MemberTypePackageDTO> findMemberPackageById(int packageId);
}
