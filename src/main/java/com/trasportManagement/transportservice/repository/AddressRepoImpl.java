package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.repository.mapper.AddressRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "addressRepo")

public class AddressRepoImpl implements AddressRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Address> findAllMembersAddress() {
        final String SQL = "SELECT * FROM Address";
        List<Address> memberList = jdbcTemplate.query(SQL, new AddressRowMapper());
        return memberList;
    }

    @Override
    public List<Address> findMemberAddressById(int memberId) {
        final String SQL = "SELECT * FROM Address WHERE memberId=:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<Address> memberList = jdbcTemplate.query(SQL, parameters, new AddressRowMapper());
        return memberList;
    }

    @Override
    public int addAddress(Address a) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Address (addressId, memberId, addLine1, addLine2, city, zipCode) VALUES (:addressId, :memberId, :addLine1, :addLine2, :city, :zipCode)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(a), holder);

    }

    @Override
    public int updateAddress(int memberId,int addressId,Address a) {
        a.setAddressId(addressId);
        a.setMemberId(memberId);
        final String SQL = "UPDATE Address SET addLine1=:addLine1, addLine2=:addLine2, city=:city, zipCode=:zipCode WHERE memberId=:memberId and addressId=:addressId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(a));
    }

    @Override
    public Boolean deleteAddress(int memberId, int addressId) {
        Address m = new Address();
        m.setAddressId(addressId);
        m.setMemberId(memberId);
        final String SQL = "DELETE FROM Address WHERE memberId=:memberId and addressId=:addressId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m)) > 0;
    }
}
