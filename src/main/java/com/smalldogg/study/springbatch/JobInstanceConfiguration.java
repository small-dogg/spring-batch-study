package com.smalldogg.study.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobInstance(){
        return jobBuilderFactory.get("jobInstance")
                .start(jobInstanceStep1())
                .next(jobInstanceStep2())
                .build();
    }

    public Step jobInstanceStep1() {
        return stepBuilderFactory.get("jobInstanceStep1")
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED).build();
    }

    public Step jobInstanceStep2() {
        return stepBuilderFactory.get("jobInstanceStep2")
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED).build();
    }
}
