package com.rogerserra.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //indicates that the class can be used by the Spring IoC container as a source of bean definitions.
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    // https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/configuration/annotation/JobBuilderFactory.html

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    //https://docs.spring.io/spring-batch/docs/current/api/org/springframework/batch/core/configuration/annotation/StepBuilderFactory.html

    @Bean // Spring bean is an object that form the backbone of your application and that is managed by the Spring IoC container.
    public Job firstJob(){
        // a job can have a single step or multiple steps, eg: leer datos,, grabar datos, etc
        return jobBuilderFactory.get("First Job") // job name
                .start(firstStep()) //start quiere un step
                .build();

    }

    private Step firstStep(){
        return stepBuilderFactory.get("First Step")
                // definir que tarea queremos con tasklet step
                .tasklet(firstTask())
                .build();
    }

    private Tasklet firstTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is my first tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }

}
