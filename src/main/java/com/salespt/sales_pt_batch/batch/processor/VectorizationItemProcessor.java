package com.salespt.sales_pt_batch.batch.processor;

import com.salespt.sales_pt_batch.model.Consult;
import com.salespt.sales_pt_batch.model.ConsultStage;
import com.salespt.sales_pt_batch.model.VectorConsultData;
import com.salespt.sales_pt_batch.service.EmbeddingService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("consultProcessor")
public class VectorizationItemProcessor implements ItemProcessor<Consult, VectorConsultData> {

    private final EmbeddingService embeddingService;
    @Autowired
    public VectorizationItemProcessor(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @Override
    public VectorConsultData process(Consult item) {
        try {
            System.out.println("테스트 : "+item.getConsultText());

            StringBuilder sb = new StringBuilder();
            if (item.getStages() != null && !item.getStages().isEmpty()) {
                sb.append("[상담 단계 정보]\n");
                for (ConsultStage stage : item.getStages()) {
                    sb.append("- ").append(stage.getStageName()).append("\n");
                }
            }
            String consultStages = sb.toString();

            float[] embeddingArray = embeddingService.getEmbedding(item.getConsultText()+consultStages);

            VectorConsultData vectorData = new VectorConsultData();
            vectorData.setConsultId(item.getId());
            vectorData.setContent(item.getConsultText());
            vectorData.setStageName(consultStages);
            vectorData.setEmbedding(embeddingArray);

            item.setVectorized(true);

            return vectorData;

        } catch (Exception e) {
            log.error("임베딩 생성 실패: Processing consult id={}", item.getId());
            log.error("error message: {}", e.getMessage());
            return null;
        }
    }
}