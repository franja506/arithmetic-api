package com.pachico.arithmetic.adapter.out.db;

import com.pachico.arithmetic.domain.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DbRepository extends JpaRepository<Operation, UUID> {

}
