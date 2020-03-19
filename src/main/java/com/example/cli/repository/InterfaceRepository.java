package com.example.cli.repository;


import com.example.cli.entity.Interface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  dao层
 * </p>
 *
 * @author lw
 * @since 2019-10-21 11:26:39
 */
@Repository
public interface InterfaceRepository extends JpaRepository<Interface,Integer>, JpaSpecificationExecutor<Interface> {

}
