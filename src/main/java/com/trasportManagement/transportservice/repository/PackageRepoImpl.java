package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PackageDTO;
import com.trasportManagement.transportservice.model.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value="packageRepo")
public class PackageRepoImpl implements PackageRopo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public int addPackage(Package p) {

        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Package (id, name, subscriptionType, counts, validity, balance, price) VALUES (:id, :name, :subscriptionType, :counts, :validity, :balance, :price)";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);

        p.setId(holder.getKey().intValue());
        final int id = holder.getKey().intValue();
        return id;
    }

    @Override
    public int updatePackage(int id, Package p) {
        p.setId(id);
        final String SQL = "UPDATE Package SET name=:name, subscriptionType=:subscriptionType, counts=:counts, validity=:validity, balance=:balance, price=:price WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public boolean deletePackage(int id) {
        Package p = new Package();
        p.setId(id);
        final String SQL = "DELETE FROM Package WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p)) > 0;
    }

    @Override
    public List<PackageDTO> findAllPackage(){
        final String SQL = "SELECT p.*, m.memberTypeName from Package as p, MemberType as m, MemberTypePackage as mp WHERE " +
                "p.id=mp.packageId and m.memberTypeId=mp.memberTypeId";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new PackageDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("subscriptionType"),
                        rs.getInt("counts"),
                        rs.getInt("validity"),
                        rs.getInt("balance"),
                        rs.getInt("price"),
                        rs.getString("memberTypeName")
                )
        );
    }

    @Override
    public List<Package> findPackageById(int id){
        final String SQL = "SELECT * FROM Package where id=:id";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        return jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new Package(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("subscriptionType"),
                        rs.getInt("counts"),
                        rs.getInt("validity"),
                        rs.getInt("balance"),
                        rs.getInt("price")
                )
        );
    }

    @Override
    public List<SubscriptionType> findAllSubscriptionType(){
        final String SQL = "select SubscriptionType from Package";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new SubscriptionType(
                        rs.getString("type")
                )
        );
    }

    @Override
    public List<Package> findPackageBySubTypeId(String subType){
        final String SQL = "SELECT * FROM Package where subscriptionType=:subType";

        SqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("subType", subType);

        return jdbcTemplate.query(SQL, parameter, (rs, i) ->
                new Package(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("subscriptionType"),
                        rs.getInt("counts"),
                        rs.getInt("validity"),
                        rs.getInt("balance"),
                        rs.getInt("price")
                )
        );
    }

    @Override
    public Optional<Integer> findValidityById(int id) {
        final String SELECT_VALIDITY = "SELECT validity FROM Package WHERE id=:packageId";

        SqlParameterSource packageId = new MapSqlParameterSource()
                .addValue("packageId", id);

        try {
            Optional<Integer> validity = Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_VALIDITY, packageId, Integer.class));
            return validity;
        }catch (EmptyResultDataAccessException e) {
            System.out.println("No record found in database for "+id);
            return Optional.empty();
        }
    }

}
