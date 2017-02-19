package eu.els.sie.xmlfirst.batch;

import eu.els.sie.xmlfirst.batch.config.BatchProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import javax.inject.Inject;

import static java.lang.System.exit;

/**
 * Created by mmarzougui on 13/02/2017.
 */
@SpringBootApplication
@EnableConfigurationProperties(BatchProperties.class)
@EnableAutoConfiguration
public class Application implements  CommandLineRunner{

    private Logger logger = LoggerFactory.getLogger(Application.class);

    @Inject
    private JobLauncher jobLauncher ;

    @Inject
    Job job;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(false).run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}