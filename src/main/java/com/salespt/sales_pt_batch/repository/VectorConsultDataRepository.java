package com.salespt.sales_pt_batch.repository;

import com.salespt.sales_pt_batch.model.VectorConsultData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VectorConsultDataRepository extends JpaRepository<VectorConsultData, Long> {
}