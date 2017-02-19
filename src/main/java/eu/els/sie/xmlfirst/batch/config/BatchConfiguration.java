package eu.els.sie.xmlfirst.batch.config;

import eu.els.sie.xmlfirst.batch.listener.JobStatusListener;
import eu.els.sie.xmlfirst.batch.reader.MongoItemReader;
import eu.els.sie.xmlfirst.batch.writer.SolrItemWriter;
import eu.els.sie.xmlfirst.ecm.core.domain.models.mongo.EditorialElement;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;

/**
 * Created by mmarzougui on 13/02/2017.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    @Inject
    private JobBuilderFactory jobBuilders;

    @Inject
    private StepBuilderFactory stepBuilders;

    @Inject
    private MongoItemReader reader ;

    @Inject
    private SolrItemWriter writer ;

    @Inject
    private JobStatusListener listener ;

    @Inject
    private BatchProperties batchProperties ;


    @Bean
    public Job BaindexEditorialElementob()
    {
        return jobBuilders.get("indexEditorialElement")
                .listener(listener)
                .flow(indexEditorialElement())
                .end()
                .build();
    }

    @Bean
    public Step indexEditorialElement()
    {
        return stepBuilders.get("indexEditorialElementStep")
                .<EditorialElement, EditorialElement>chunk(10)
                .reader(reader)
                .writer(writer)
                .faultTolerant()
                .skip(RuntimeException.class)
                .taskExecutor(taskExecutor())
                .throttleLimit(batchProperties.getThrottleLimit())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(batchProperties.getThreadPoolSize());
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

}
