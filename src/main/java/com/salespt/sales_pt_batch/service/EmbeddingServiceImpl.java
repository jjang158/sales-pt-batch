package com.salespt.sales_pt_batch.service;

import com.theokanning.openai.embedding.Embedding;
import com.theokanning.openai.embedding.EmbeddingRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmbeddingServiceImpl implements EmbeddingService {

    private final OpenAiService openAiService;

    public EmbeddingServiceImpl(@Value("${spring.ai.openai.api-key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    @Override
    public float[] getEmbedding(String text) {
        // OpenAI 임베딩 요청
        EmbeddingRequest request = EmbeddingRequest.builder()
                .model("text-embedding-3-small")
                .input(Collections.singletonList(text))
                .build();

        List<Embedding> embeddings = openAiService.createEmbeddings(request).getData();

        // 첫 번째 결과만 사용
        List<Double> embeddingList = embeddings.get(0).getEmbedding();

        // float[] 변환
        float[] embeddingArray = new float[embeddingList.size()];
        for (int i = 0; i < embeddingList.size(); i++) {
            embeddingArray[i] = embeddingList.get(i).floatValue();
        }

        return embeddingArray;
    }
}
