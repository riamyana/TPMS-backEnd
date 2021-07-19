package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypePackageDTO;
import com.trasportManagement.transportservice.model.PackageForMember;
import com.trasportManagement.transportservice.repository.mapper.MemberTypePackageExtractor;
import com.trasportManagement.transportservice.repository.mapper.PackageForMemberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="MemberTypePackageRepo")
public class MemberTypePackageRepoImpl implements MemberTypePackageRepo{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addMemberTypePackage(MemberTypePackage mp) {

        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO MemberTypePackage (id, packageId, memberTypeId, discountStartDate, discountEndDate, " +
        "discountPercentage, discountDescription) VALUES (:id, :packageId, :memberTypeId, :discountStartDate, :discountEndDate, " +
        ":discountPercentage, :discountDescription)";

        int param = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp), holder);

        if (param == 0) {
            throw new TPMSCustomException("No record inserted of this Member Type Package.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        mp.setId(holder.getKey().intValue());
        return holder.getKey().intValue();

    }

    @Override
    public int updateMemberTypePackage(int id, MemberTypePackage mp) {
        mp.setId(id);
        final String SQL = "UPDATE Package SET packageId=:packageId, memberTypeId=:memberTypeId, " +
                "discountStartDate=:discountStartDate, discountEndDate=:discountEndDate, discountPercentage=:discountPercentage, " +
                "discountDescription=:discountDescription WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp));
    }

    @Override
    public boolean deleteMemberTypePackage(int id) {
        MemberTypePackage mp = new MemberTypePackage();
        mp.setId(id);
        final String SQL = "DELETE FROM MemberTypePackage WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp)) > 0;
    }

    @Override
    public List<MemberTypePackageDTO> findMemberPackageById(int packageId) {
        final String SQL = "SELECT p.*, mt.id as memberPacakgeId, mt.packageId, mt.memberTypeId, mt.discountStartDate, " +
                "mt.discountEndDate, mt.discountPercentage, mt.discountDescription FROM Package AS p, MemberType " +
                "as m, MemberTypePackage as mt WHERE p.id=mt.packageId and m.memberTypeId=mt.memberTypeId and " +
                "p.id=:packageId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("packageId", packageId);

        List<MemberTypePackageDTO> result = (List<MemberTypePackageDTO>) jdbcTemplate.query(SQL, parameters, new MemberTypePackageExtractor());
        return result;
    }

    @Override
    public List<PackageForMember> findMemberPackageByMemberId(int memberId) {
        final String SQL = "SELECT p.*, mt.memberTypeId, mt.id as memberPackageId, mt.discountStartDate, mt.discountEndDate, " +
                "mt.discountPercentage, mt.discountDescription FROM Package AS p, MemberType as m, MemberTypePackage " +
                "as mt WHERE p.id=mt.packageId and m.memberTypeId=mt.memberTypeId and  mt.memberTypeId =:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<PackageForMember> result = jdbcTemplate.query(SQL, parameters, new PackageForMemberRowMapper());
        return result;
    }

    @Override
    public List<PackageForMember> findAllMemberPackage() {
        final String SQL = "SELECT p.*, mt.memberTypeId, mt.id as memberPackageId, mt.discountStartDate, mt.discountEndDate, " +
                "mt.discountPercentage, mt.discountDescription FROM Package AS p, MemberType as m, MemberTypePackage " +
                "as mt WHERE p.id=mt.packageId and m.memberTypeId=mt.memberTypeId";

        List<PackageForMember> result = jdbcTemplate.query(SQL, new PackageForMemberRowMapper());
        return result;
    }
}
