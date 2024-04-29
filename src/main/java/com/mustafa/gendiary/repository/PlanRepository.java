package com.mustafa.gendiary.repository;

import com.mustafa.gendiary.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {
}
