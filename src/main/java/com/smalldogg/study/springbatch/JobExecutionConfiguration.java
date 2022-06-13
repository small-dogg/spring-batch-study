package com.smalldogg.study.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobExecutionConfiguration {

    /**
     * Job 수행 결과에 따라 Job은 다시 수행할 수 있다.
     */
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobExecution(){
        return jobBuilderFactory.get("jobExecution")
                .start(jobExecutionStep1())
                .next(jobExecutionStep2())
                .build();
    }

    public Step jobExecutionStep1() {
        return stepBuilderFactory.get("jobExecutionStep1")
                .tasklet((contribution, chunkContext) -> {
//                    throw new RuntimeException("step1 has failed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    public Step jobExecutionStep2() {
        return stepBuilderFactory.get("jobExecutionStep2")
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED).build();
    }
}
