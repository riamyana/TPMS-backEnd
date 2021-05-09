package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.repository.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Qualifier("discountRepo")
    @Autowired
    DiscountRepo discountRepo;

    public Discount addDiscount(Discount d){
        int n = discountRepo.addDiscount(d);

        if (n == 0) {
            throw new TPMSCustomException("No record inserted of this discount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return d;
    }

    public Discount updateDiscount(int id, Discount d) throws Exception{
        int n = discountRepo.updateDiscount(id,d);
        d.setId(id);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given Discount id : "+ id, HttpStatus.NOT_FOUND);
        }

        return d;
    }

    public Boolean deleteDiscount(int id){
        if(!discountRepo.deleteDiscount(id)){
            throw new TPMSCustomException("Unable to delete discount. Given discount id : " + id + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    public List<Discount> findAllDiscounts(int packageId) throws TPMSCustomException {
        List<Discount> result = discountRepo.findAllDiscount(packageId);

        if(result.isEmpty()){
            throw new TPMSCustomException("No discount found", HttpStatus.NOT_FOUND);
        }

        return result;
    }
}
