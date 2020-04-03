package com.example.cloudfoundrydatabase;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcessListEntry {
    @Id
    Integer id;
    String host;

    public String toString() {
        return host;
    }

    public String getHost() {
        return host;
    }

}
