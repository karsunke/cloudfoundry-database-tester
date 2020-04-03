package com.example.cloudfoundrydatabase;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataBaseJob implements Job {

    @Autowired
    private TestRepository testRepository;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("\n" + LocalDateTime.now() + ": Executing test query.");

        List<ProcessListEntry> processList = testRepository.testQuery();

        System.out.println("Number of connections: " + processList.size());
        System.out.println("Hostnames: " + processList.stream()
                .map(ProcessListEntry::getHost)
                .map(host -> host.split(":")[0])
                .distinct()
                .collect(Collectors.joining(", ")));
    }
}