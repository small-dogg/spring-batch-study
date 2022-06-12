package com.smalldogg.study.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobParameterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobParameter(){
        return jobBuilderFactory.get("jobParameter")
                .start(jobParameterStep1())
                .next(jobParameterStep2())
                .build();
    }

    public Step jobParameterStep1() {
        return stepBuilderFactory.get("jobParameterStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("jobParameterStep1");
                    //StepContribution -> StepExecution -> JobExecution -> JobParameters

                    JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
                    String name = jobParameters.getString("name");
                    Long seq = jobParameters.getLong("seq");
                    Date date = jobParameters.getDate("date");
                    Double height = jobParameters.getDouble("height");
                    System.out.println("name = " + name);
                    System.out.println("seq = " + seq);
                    System.out.println("date = " + date);
                    System.out.println("height = " + height);

                    Map<String, Object> jobParametersMap = chunkContext.getStepContext().getJobParameters();
                    jobParametersMap.forEach((k,v)-> System.out.println(k+":"+v));

                    return RepeatStatus.FINISHED;
                }).build();
    }

    public Step jobParameterStep2() {
        return stepBuilderFactory.get("jobParameterStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("jobParameterStep1");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
