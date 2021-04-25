package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="packageRepo")
public class PackageRepoImpl implements PackageRopo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public int addPackage(Package p) {

        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Package (memberType, name, subscriptionType, counts, validity, balance, price) VALUES (:memberType, :name, :subscriptionType, :counts, :validity, :balance, :price)";
        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);

        if(n > 0){
            return holder.getKey().intValue();
        }

        return 0;
    }

    @Override
    public int updatePackage(int id, Package p) {
        p.setId(id);
        final String SQL = "UPDATE Package SET memberType=:memberType, name=:name, subscriptionType=:subscriptionType, counts=:counts, validity=:validity, balance=:balance, price=:price WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public int deletePackage(int id) {
        Package p = new Package();
        p.setId(id);
        final String SQL = "DELETE FROM Package WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public List<Package> findAllPackage(){
        final String SQL = "SELECT * FROM Package";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Package(
                        rs.getInt("id"),
                        rs.getInt("memberType"),
                        rs.getString("name"),
                        rs.getInt("subscriptionType"),
                        rs.getInt("counts"),
                        rs.getInt("validity"),
                        rs.getInt("balance"),
                        rs.getInt("price")
                )
        );
    }

    @Override
    public List<Package> findPackageById(int id){
        final String SQL = "SELECT * FROM Package where id="+id;
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Package(
                        rs.getInt("id"),
                        rs.getInt("memberType"),
                        rs.getString("name"),
                        rs.getInt("subscriptionType"),
                        rs.getInt("counts"),
                        rs.getInt("validity"),
                        rs.getInt("balance"),
                        rs.getInt("price")
                )
        );
    }

    @Override
    public List<SubscriptionType> findAllSubscriptionType(){
        final String SQL = "select * from SubscriptionType";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new SubscriptionType(
                        rs.getString("type")
                )
        );
    }

}
