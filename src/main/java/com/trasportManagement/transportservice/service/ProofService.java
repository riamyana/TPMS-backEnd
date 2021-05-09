package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.repository.ProofRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProofService {
    @Autowired
    @Qualifier("proofRepo")
    ProofRepo proofRepo;

    public List<ProofWithMemberType> findAllProofs(){
        List<ProofWithMemberType> proofList = proofRepo.findAllProofs();
        if(proofList.isEmpty()){
            throw  new TPMSCustomException("No proofs found.", HttpStatus.NOT_FOUND);
        }

        return proofList;
    }

    public List<Proof> findProofsByMemberTypeId(int memberTypeId) {
        List<Proof> proofList = proofRepo.findProofsByMemberTypeId(memberTypeId);
        if(proofList.isEmpty()) {
            throw new TPMSCustomException("No proofs found for MemberType ID : " + memberTypeId, HttpStatus.NOT_FOUND);
        }

        return proofList;
    }

    public Proof addProof(Proof p) {
        int n = proofRepo.addProof(p);
        if(n == 0){
            throw new TPMSCustomException("No record inserted for this Proof", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return p;
    }

    public Proof updateProof(int proofId, Proof p) {
        int n = proofRepo.updateProof(proofId, p);
        p.setProofId(proofId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given proof id : " + proofId   + " not found.", HttpStatus.NOT_FOUND);
        }

        return p;
    }

    public Boolean deleteProof(int proofId) {
        if(!proofRepo.deleteProof(proofId)){
            throw new TPMSCustomException("Unable to delete. Given proof id : " + proofId + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

}
