package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.repository.ProofRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProofService {
    @Autowired
    @Qualifier("proofRepo")
    ProofRepo proofRepo;

    public Result<List<ProofWithMemberType>> findAllProofs(){
        List<ProofWithMemberType> proofList = proofRepo.findAllProofs();
        if(proofList.size() > 0){
            return new Result<>(200, proofList);
        }
        else{
            return new Result<>(400, "No proof found.");
        }
    }

    public Result<Proof> addProof(Proof p) {
        int proofId = proofRepo.addProof(p);
        if(proofId == 0){
            return new Result<>(400, "Error in adding Proof.");
        }else{
            p.setProofId(proofId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, p);
        }
    }

    public Result<Proof> updateProof(int proofId, Proof p) {
        int n = proofRepo.updateProof(proofId, p);
        if(n > 0){
            return new Result<>(200, p);
        }
        else{
            return new Result<>(400, "Unable to update. Given proof id : " + proofId   + " not found.");
        }
    }

    public Result<Proof> deleteProof(int proofId) {
        int n = proofRepo.deleteProof(proofId);
        if(n > 0){
            return new Result<>(200, "Proof with id : " + proofId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given proof id : " + proofId + " not found.");
        }
    }
}
