package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepo;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepo = timeEntryRepository;
    }

    @RequestMapping(value="/time-entries", method=RequestMethod.POST)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        ResponseEntity<TimeEntry> response;
        TimeEntry result = this.timeEntryRepo.create(timeEntryToCreate);
        if (result != null) {
            response = new ResponseEntity<TimeEntry>(result, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<TimeEntry>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value="/time-entries/{timeEntryId}", method=RequestMethod.GET)
    public ResponseEntity<TimeEntry> read( @PathVariable("timeEntryId") long timeEntryId) {
        ResponseEntity<TimeEntry> response;
        TimeEntry result = this.timeEntryRepo.find(timeEntryId);
        if (result != null) {
            response = new ResponseEntity<TimeEntry>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<TimeEntry>(null, null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value="/time-entries", method=RequestMethod.GET)
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> response;
        List<TimeEntry> result = this.timeEntryRepo.list();
        if (result != null) {
            response = new ResponseEntity<List<TimeEntry>>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<List<TimeEntry>>(null, null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value="/time-entries/{timeEntryId}", method=RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("timeEntryId") long timeEntryId, @RequestBody TimeEntry expected) {
        ResponseEntity<TimeEntry> response;
        TimeEntry result = this.timeEntryRepo.update(timeEntryId, expected);
        if (result != null) {
            response = new ResponseEntity<TimeEntry>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<TimeEntry>(null, null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @RequestMapping(value="/time-entries/{timeEntryId}", method=RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("timeEntryId") long timeEntryId) {
        ResponseEntity response;
        this.timeEntryRepo.delete(timeEntryId);
        response = new ResponseEntity(HttpStatus.NO_CONTENT);
        return response;
    }
}
