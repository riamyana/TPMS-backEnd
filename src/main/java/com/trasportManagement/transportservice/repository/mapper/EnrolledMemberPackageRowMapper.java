package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.EnrolledMemberPackage;
import com.trasportManagement.transportservice.model.PackageForMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrolledMemberPackageRowMapper implements RowMapper<EnrolledMemberPackage> {
    @Override
    public EnrolledMemberPackage mapRow(ResultSet rs, int i) throws SQLException {
        EnrolledMemberPackage ep = new EnrolledMemberPackage();
        ep.setPassId(rs.getInt("passId"));
        ep.setPackageId(rs.getInt("packageId"));
        ep.setStart(rs.getDate("start"));
        ep.setEnd(rs.getDate("end"));
        ep.setName(rs.getString("name"));
        ep.setSubscriptionType(rs.getString("subscriptionType"));
        ep.setAmount(rs.getDouble("amount"));
        ep.setTransportMode(rs.getString("transportMode"));
        return ep;
    }
}
