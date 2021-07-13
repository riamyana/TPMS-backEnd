package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepo {
    public int addPass(Pass p);
    public int updatePass(int passId, Pass p);
    public boolean deletePass(int passId);
    public List<PassWithMemberDetails> findAllMemberPasses();

    List<PassWithMemberDetails> findMemberPassesByMemberId(int memberId);

    public List<Pass> findAllPasses();
    public List<PassWithMemberDetails> findMemberPassById(int passId);
    public List<PassWithMemberDetails> findMemberPassBySerialNo(Long serialNo);
//    public Date findExpiryBySerialNo(Long serialNo);
}
