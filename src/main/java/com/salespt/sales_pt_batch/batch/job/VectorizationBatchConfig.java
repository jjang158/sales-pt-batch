package com.salespt.sales_pt_batch.batch.job;

import com.salespt.sales_pt_batch.model.Consult;
import com.salespt.sales_pt_batch.model.VectorConsultData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class VectorizationBatchConfig {

    @Bean
    public Job vectorizationJob(JobRepository jobRepository,
                                Step vectorizationStep) {
        return new JobBuilder("vectorizationJob", jobRepository)
                .start(vectorizationStep)
                .build();
    }

    @Bean
    public Step vectorizationStep(JobRepository jobRepository,
                                  PlatformTransactionManager transactionManager,
                                  ItemReader<Consult> consultReader,
                                  ItemProcessor<Consult, VectorConsultData> consultProcessor,
                                  ItemWriter<VectorConsultData> consultWriter) {
        return new StepBuilder("vectorizationStep", jobRepository)
                .<Consult, VectorConsultData>chunk(5, transactionManager)
                .reader(consultReader)
                .processor(consultProcessor)
                .writer(consultWriter)
                .build();
    }
}