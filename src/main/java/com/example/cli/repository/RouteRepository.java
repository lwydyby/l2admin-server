package com.example.cli.repository;


import com.example.cli.entity.Route;
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
 * @since 2019-10-21 11:26:39
 */
@Repository
public interface RouteRepository extends JpaRepository<Route,Integer>, JpaSpecificationExecutor<Route> {
    List<Route> findAllByLock(Integer lock);
}
