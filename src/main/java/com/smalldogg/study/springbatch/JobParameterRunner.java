package com.smalldogg.study.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JobParameterRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final Job jobParameter;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /**
         * Job Parameter의 타입으로는 String, Long, Date, Double을 사용할 수 있다.
         *
         */

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user4")
                .addLong("seq",3L)
                .addDate("date", new Date())
                .addDouble("height",161.5)
                .toJobParameters();

        jobLauncher.run(jobParameter, jobParameters);
    }
}
