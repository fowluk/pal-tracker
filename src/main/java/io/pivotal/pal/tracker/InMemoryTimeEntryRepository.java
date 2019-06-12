package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.JsonSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    private HashMap<Long, TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();
    private Long maxId = 1L;

    public synchronized TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(this.maxId);
        this.timeEntries.put(maxId, timeEntry);
        this.maxId++;
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return this.timeEntries.getOrDefault(id, null);
    }

    public List<TimeEntry> list() {
        return new ArrayList(this.timeEntries.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (this.timeEntries.containsKey(id)) {
            timeEntry.setId(id);
            this.timeEntries.put(id, timeEntry);
            return timeEntry;
        } else {
            return null;
        }
    }

    public void delete(long id){
        this.timeEntries.remove(id);
    }
}

