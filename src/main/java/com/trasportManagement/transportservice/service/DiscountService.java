package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.repository.DiscountRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Qualifier("discountRepo")
    @Autowired
    DiscountRepo discountRepo;

    public Result<Discount> addDiscount(Discount d){
        int id = discountRepo.addDiscount(d);

        if(id == 0){
            return new Result<>(400, "Error in adding discount.");
        }else{
            d.setId(id);
            return new Result<>(201, d);
        }
    }

    public Result<Discount> updateDiscount(int id, Discount d){
        int n = discountRepo.updateDiscount(id,d);

        if(n > 0){
            return new Result<>(200, d);
        }
        else{
            return new Result<>(400, "Unable to update the discount for package");
        }
    }

    public Result<Discount> deleteDiscount(int id){
        int n = discountRepo.deleteDiscount(id);
        if(n > 0){
            return new Result<>(200, "Discount of package with discount id : " + id + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete discount. Given discount id : " + id + " not found.");
        }
    }

    public Result<List<Discount>> findAllDiscounts(int packageId){
        List<Discount> result = discountRepo.findAllDiscounts(packageId);
        if(result.size() > 0){
            return new Result<>(200, result);
        }
        else{
            return new Result<>(400, "No discount found.");
        }
    }
}
