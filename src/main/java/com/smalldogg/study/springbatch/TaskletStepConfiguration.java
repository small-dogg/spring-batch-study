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
public class TaskletStepConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job taskletStep(){
        return jobBuilderFactory.get("taskletStep")
                .start(taskletStepStep1())
                .next(taskletStepStep2())
                .build();
    }

    public Step taskletStepStep1() {
        //AbstractTaskletStepBuilder에 의해 step이 만들어짐
        return stepBuilderFactory.get("taskletStepStep1")
                .tasklet((contribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                }).build();
    }

    public Step taskletStepStep2() {
        return stepBuilderFactory.get("taskletStepStep2")
                .tasklet(new CustomTasklet()).build();
    }
}
