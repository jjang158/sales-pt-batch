package com.salespt.sales_pt_batch.batch.reader;

import com.salespt.sales_pt_batch.model.Consult;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultItemReader {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ConsultItemReader(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean(name = "consultReader")
    public JpaPagingItemReader<Consult> consultReader() {
        return new JpaPagingItemReaderBuilder<Consult>()
                .name("consultReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM Consult c WHERE c.isVectorized = false")
                .pageSize(100)
                .build();
    }
}