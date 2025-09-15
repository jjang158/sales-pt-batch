package com.salespt.sales_pt_batch.batch.writer;

import com.salespt.sales_pt_batch.model.VectorConsultData;
import com.salespt.sales_pt_batch.repository.ConsultRepository;
import com.salespt.sales_pt_batch.repository.VectorConsultDataRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("consultWriter")
public class VectorConsultDataItemWriter implements ItemWriter<VectorConsultData> {

    private final ConsultRepository consultRepository;
    private final VectorConsultDataRepository vectorConsultDataRepository;

    @Autowired
    public VectorConsultDataItemWriter(ConsultRepository consultRepository,
                                   VectorConsultDataRepository vectorConsultDataRepository) {
        this.consultRepository = consultRepository;
        this.vectorConsultDataRepository = vectorConsultDataRepository;
    }

    @Override
    public void write(Chunk<? extends VectorConsultData> items) throws Exception {
        // 1. Consult ID 목록 추출
        List<Long> consultIds = items.getItems().stream()
                                .map(VectorConsultData::getConsultId)
                                .collect(Collectors.toList());

        // 2. vectorize 처리 플래그 업데이트
        consultRepository.updateVectorizedFlags(consultIds);

        // 3. VectorConsultData bulk insert
        vectorConsultDataRepository.saveAll(items.getItems());
    }
}