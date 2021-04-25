package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepo {
    public int addDiscount(Discount d);
    public int updateDiscount(int id, Discount d);
    public int deleteDiscount(int id);
    public List<Discount> findAllDiscounts(int packageId);
}
