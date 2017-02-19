package eu.els.sie.xmlfirst.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

@Component
public class JobStatusListener implements JobExecutionListener
{
    private static final Logger  LOGGER = LoggerFactory.getLogger(JobStatusListener.class);


    public void afterJob(JobExecution jobExecution)
    {
        StringBuilder protocol = new StringBuilder();
        protocol.append("\n----------------------------------------------------- \n");
        protocol.append("Protocol for " + jobExecution.getJobInstance().getJobName() + " \n");
        protocol.append("  Started     : " + jobExecution.getStartTime() + "\n");
        protocol.append("  Finished    : " + jobExecution.getEndTime() + "\n");
        protocol.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
        protocol.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
        protocol.append("  Status      : " + jobExecution.getStatus() + "\n");
        protocol.append("  Duration      : " + Duration.between(jobExecution.getStartTime().toInstant(),jobExecution.getEndTime().toInstant()).getSeconds() + "\n");
        protocol.append("-------------------------------------------------------- \n");

        protocol.append("Job-Parameter: \n");
        JobParameters jp = jobExecution.getJobParameters();
        for (Iterator<Map.Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext(); )
        {
            Map.Entry<String, JobParameter> entry = iter.next();
            protocol.append("  " + entry.getKey() + "=" + entry.getValue() + "\n");
        }
        protocol.append("-------------------------------------------------------- \n");

        for (StepExecution stepExecution : jobExecution.getStepExecutions())
        {
            protocol.append("\n-------------------------------------------------- \n");
            protocol.append("Step " + stepExecution.getStepName() + " \n");
            protocol.append("WriteCount: " + stepExecution.getWriteCount() + "\n");
            protocol.append("Commits: " + stepExecution.getCommitCount() + "\n");
            protocol.append("SkipCount: " + stepExecution.getSkipCount() + "\n");
            protocol.append("Rollbacks: " + stepExecution.getRollbackCount() + "\n");
            protocol.append("Filter: " + stepExecution.getFilterCount() + "\n");
            protocol.append("---------------------------------------------------- \n");
        }
        LOGGER.info(protocol.toString());
    }

    public void beforeJob(JobExecution jobExecution)
    {
        LOGGER.info("start indexation job : '{}'", LocalDateTime.now());
    }

}
