package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d), holder);

        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public int updateDiscount(int id, Discount d) throws Exception{
        d.setId(id);
        final String SQL = "UPDATE Discount SET packageId=:packageId, startDate=:startDate, endDate=:endDate, percentage=:percentage, description=:description WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d));
    }

    @Override
    public boolean deleteDiscount(int id){
        Discount d = new Discount();
        d.setId(id);
        final String SQL = "DELETE FROM Discount WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(d)) > 0;
    }

//    @Override
//    public Optional<Discount> findAllDiscounts(int packageId){
//        final String SQL = "SELECT * FROM Discount where packageId=:packageId";
//
//        SqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("packageId", packageId);
//        try {
//            return jdbcTemplate.queryForObject(SQL, parameters, (rs, rowNum) ->
//                    Optional.of(new Discount(
//                            rs.getInt("id"),
//                            rs.getInt("packageId"),
//                            rs.getDate("startDate"),
//                            rs.getDate("endDate"),
//                            rs.getInt("percentage"),
//                            rs.getString("description")
//                    )));
//        }catch (EmptyResultDataAccessException e) {
////            System.out.println("No record found in database for "+name);
//            return Optional.empty();
//        }
//
//    }

    @Override
    public List<Discount> findAllDiscount(int packageId){
        final String SQL = "SELECT * FROM Discount where packageId=:packageId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("packageId", packageId);

        List<Discount> resultList =  jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new Discount(
                        rs.getInt("id"),
                        rs.getInt("packageId"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getInt("percentage"),
                        rs.getString("description")
                )
        );

        return resultList;
    }
}
