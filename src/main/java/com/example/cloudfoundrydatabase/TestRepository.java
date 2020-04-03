package com.example.cloudfoundrydatabase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TestRepository extends Repository<ProcessListEntry, String> {

    @Query(value = "select id, host from information_schema.processlist;", nativeQuery = true)
    List<ProcessListEntry> testQuery();

}
