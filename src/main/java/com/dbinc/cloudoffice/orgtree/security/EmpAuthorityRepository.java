package com.dbinc.cloudoffice.orgtree.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpAuthorityRepository extends JpaRepository<EmpAuthority, Long>
{
  List<EmpAuthority> findByEmail(String email);
}
