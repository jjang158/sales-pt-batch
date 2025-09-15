package com.salespt.sales_pt_batch.repository;

import com.salespt.sales_pt_batch.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    @Modifying
    @Query("UPDATE Consult SET isVectorized = true WHERE id IN :ids")
    void updateVectorizedFlags(@Param("ids") List<Long> ids);
}