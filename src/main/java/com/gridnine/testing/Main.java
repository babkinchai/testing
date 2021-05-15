package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int DELAY_HOUR=2;
        LocalDateTime localDateTime=LocalDateTime.now();
        List<Flight> flight= FlightBuilder.createFlights();
        FilterService fIlterService = new FilterService();
        List<Flight> depBeforeArrList=fIlterService.departureBeforeArrival(flight);
        List<Flight> depBeforeNowTime=fIlterService.departureBeforeNow(flight);
        List<Flight> depArrDifferenceMore = fIlterService.delayMore(flight, DELAY_HOUR);
        System.out.println("Вылет раньше "+localDateTime);
        fIlterService.flightPrint(depBeforeNowTime);
        System.out.println("Вылет раньше прилёта");
        fIlterService.flightPrint(depBeforeArrList);
        System.out.println("интервал между прилётом и вылетом больше "+DELAY_HOUR);
        fIlterService.flightPrint(depArrDifferenceMore);
    }
}
