package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "discountRepo")
public class DiscountRepoImpl implements DiscountRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addDiscount(Discount d){
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Discount (packageId, startDate, endDate, percentage, description) VALUES (:packageId, :startDate, :endDate, :percentage, :description)";

        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d), holder);

        if(n > 0){
            return holder.getKey().intValue();
        }

        return 0;
    }

    @Override
    public int updateDiscount(int id, Discount d){
        d.setId(id);
        final String SQL = "UPDATE Discount SET packageId=:packageId, startDate=:startDate, endDate=:endDate, percentage=:percentage, description=:description WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d));
    }

    @Override
    public int deleteDiscount(int id){
        Discount d = new Discount();
        d.setId(id);
        final String SQL = "DELETE FROM Discount WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d));
    }

    @Override
    public List<Discount> findAllDiscounts(int packageId){

        final String SQL = "SELECT * FROM Discount where packageId="+packageId;
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Discount(
                        rs.getInt("id"),
                        rs.getInt("packageId"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getInt("percentage"),
                        rs.getString("description")
                )
        );
    }
}
