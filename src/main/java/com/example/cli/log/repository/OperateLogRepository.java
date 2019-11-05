package com.example.cli.log.repository;

import com.example.cli.log.entity.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateLogRepository extends JpaRepository<OperateLog,Integer>, JpaSpecificationExecutor<OperateLog> {
}
