package com.salespt.sales_pt_batch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "consult_stage")
public class ConsultStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 상담단계 ID (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consult_id", nullable = false)
    private Consult consult;  // 상담 정보 (FK → consult.id)

    @Column(name = "stage_meta_id", nullable = false)
    private Long stageMetaId;  // 영업단계 ID (FK → sales_stage_meta.id)

    @Column(name = "stage_name", nullable = false, length = 50)
    private String stageName;  // 단계명 (스냅샷 저장)
}
