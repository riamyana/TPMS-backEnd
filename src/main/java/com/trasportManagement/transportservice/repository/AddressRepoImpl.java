package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Address;


import com.trasportManagement.transportservice.repository.mapper.AddressRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql="SELECT * FROM Address";
        List<Address> memberList = jdbcTemplate.query(sql, new AddressRowMapper());
        return memberList;
    }

    @Override
    public List<Address> findMemberAddressById(int memberId) {
        String sql="SELECT * FROM Address WHERE memberId=" + memberId;
        List<Address> memberList = jdbcTemplate.query(sql, new AddressRowMapper());
        return memberList;
    }

    @Override
    public int addAddress(Address a) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Address (addressId, memberId, addLine1, addLine2, city, zipCode) VALUES (:addressId, :memberId, :addLine1, :addLine2, :city, :zipCode)";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(a), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else
            return 0;
    }

    @Override
    public int updateAddress(int memberId,int addressId,Address a) {
        a.setAddressId(addressId);
        a.setMemberId(memberId);
        String sql = "UPDATE Address SET addLine1=:addLine1, addLine2=:addLine2, city=:city, zipCode=:zipCode WHERE memberId=:memberId and addressId=:addressId";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(a));
        return n;
    }

    @Override
    public int deleteAddress(int memberId,int addressId) {
        Address m = new Address();
        m.setAddressId(addressId);
        m.setMemberId(memberId);
        String sql = "DELETE FROM Address WHERE memberId=:memberId and addressId=:addressId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m));
    }

}
