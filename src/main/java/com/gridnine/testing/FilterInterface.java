package com.gridnine.testing;

import java.util.List;

public interface FilterInterface {

    List<Flight> departureBeforeArrival(List<Flight> allFlightList);

    List<Flight> departureBeforeNow(List<Flight> allFlightList);

    List<Flight> delayMore(List<Flight> allFlightList, int hour);

    void flightPrint(List<Flight> allFlightList);

}
