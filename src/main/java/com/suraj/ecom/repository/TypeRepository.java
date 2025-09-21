package com.suraj.ecom.repository;

import com.suraj.ecom.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> { // Changed to Long
}
