package com.dbinc.cloudoffice.orgtree.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpMasterRepository extends JpaRepository<EmpMaster, Long>
{
  Optional<EmpMaster> findByEmail(String email);
}
