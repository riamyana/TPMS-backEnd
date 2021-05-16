package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo {
    public int addDiscount(Discount d);
    public int updateDiscount(int id, Discount d) throws Exception;
    public boolean deleteDiscount(int id);
    public List<Discount> findAllDiscount(int packageId);
//    public Optional<Discount> findAllDiscounts(int packageId);
}
