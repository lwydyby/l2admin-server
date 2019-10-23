package com.example.cli.repository;


import com.example.cli.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  dao层
 * </p>
 *
 * @author lw
 * @since 2019-10-21 11:26:40
 */
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser,Integer>, JpaSpecificationExecutor<RoleUser> {

    RoleUser findByUser_IdAndRole_Id(String userId,String roleId);

    List<RoleUser> findAllByUser_id(String userId);

    List<RoleUser> findAllByRole_id(String roleID);
}
