package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FirestTests {

    private FilterService filterService;

    @BeforeEach
    public void setUp() {
        filterService = new FilterService();
    }

    @Test
    @DisplayName("boarding an hour before departure")
    void flightCheckerTest1() {
        LocalDateTime timesNow = LocalDateTime.now();
        List<Segment> segmentList = new ArrayList<Segment>();
        segmentList.add(new Segment(timesNow, timesNow.minusHours(1)));
        Flight flight = new Flight(segmentList);
        assertEquals(true,
                filterService.flightSegmentCheck(flight),
                "boarding an hour later departure"
        );
    }

    @Test
    @DisplayName("departure of the next segment before the landing of the current segment")
    void flightCheckerTest2() {
        LocalDateTime timesNow = LocalDateTime.now();
        List<Segment> segmentList = new ArrayList<Segment>();
        segmentList.add(new Segment(timesNow, timesNow.plusHours(2)));
        segmentList.add(new Segment(timesNow.plusHours(1), timesNow.plusHours(2)));
        Flight flight = new Flight(segmentList);
        assertEquals(true,
                filterService.flightSegmentCheck(flight),
                "departure of the next segment later than the landing of the current segment"
        );
    }
}
