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
public class JobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    //Job에 대해 학습
    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .start(step2_1())
                .next(step2_2())
                .build();
    }

    public Step step2_2() {
        return stepBuilderFactory.get("step2-2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step2-2 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2_1() {
        return stepBuilderFactory.get("step2-1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step2-1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
