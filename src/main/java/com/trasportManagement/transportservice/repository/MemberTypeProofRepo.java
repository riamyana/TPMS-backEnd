package com.trasportManagement.transportservice.repository;
import com.trasportManagement.transportservice.model.MemberTypeProof;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTypeProofRepo {
    public int addMemberTypeProof(MemberTypeProof mp);
    public int updateMemberTypeProof(int id, MemberTypeProof mp);
    public boolean deleteMemberTypeProof(int id);
//    public List<MemberTypePackageDTO> findMemberPackageById(int packageId);
}
