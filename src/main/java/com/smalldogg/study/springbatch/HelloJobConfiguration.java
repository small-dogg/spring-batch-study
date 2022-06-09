package com.smalldogg.study.springbatch;

import lombok.AllArgsConstructor;
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
public class HelloJobConfiguration { //Job을 정의

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")//JobBuilderFactory를 통해 Job 생성
                .start(helloStep1()) // Step을 할당
                .next(helloStep2()) // Step을 할당
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")//StepBuilderFactory를 통해 Step을 생성
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("=====================");
                        System.out.println(" >> Hello SpringBatch");
                        System.out.println("=====================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")//StepBuilderFactory를 통해 Step을 생성
                .tasklet((contribution, chunkContext) -> { //Step 안에서 단일 태스크로 수행되는 로직을 구현
                    System.out.println("=====================");
                    System.out.println(" >> Step2 was executed");
                    System.out.println("=====================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
