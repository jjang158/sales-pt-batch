package com.salespt.sales_pt_batch.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "consult")
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consult_date")
    private LocalDateTime consultDate;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "consult_text")
    private String consultText;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "is_vectorized")
    private boolean isVectorized;

    @OneToMany(mappedBy = "consult", fetch = FetchType.LAZY)
    private List<ConsultStage> stages;
}
