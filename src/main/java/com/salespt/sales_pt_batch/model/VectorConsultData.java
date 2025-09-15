package com.salespt.sales_pt_batch.model;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "vector_consult_data")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VectorConsultData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consult_id")
    private Long consultId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "stage_name", length = 50)
    private String stageName;

    @Type(FloatArrayVectorType.class)
    @Column(name = "embedding", columnDefinition = "VECTOR(1536)")
    private float[] embedding;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

}
