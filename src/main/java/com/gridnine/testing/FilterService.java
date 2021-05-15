package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FilterService implements FilterInterface{
    @Override
    public List<Flight> departureBeforeArrival(List<Flight> allFlightList) {
        return allFlightList.stream().filter(this::flightSegmentCheck).collect(Collectors.toList());
    }

    public boolean flightSegmentCheck(Flight flight) {
        for (int j = 0; j < flight.getSegments().size(); j++) {
            if (!flight.getSegments().get(j).getArrivalDate().isBefore(flight.getSegments().get(j).getDepartureDate())) {
                Segment segment = flight.getSegments().get(j);
                if(j<flight.getSegments().size()-1){
                    return flight.
                            getSegments()
                            .get(j + 1)
                            .getDepartureDate()
                            .isBefore(segment.getArrivalDate());
                }
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Flight> departureBeforeNow(List<Flight> allFlightList) {

        return allFlightList.stream().filter(
                flight -> flight.getSegments().stream().allMatch(
                        segment -> segment.getDepartureDate().isBefore(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> delayMore(List<Flight> allFlightList, int hour) {
        List<Flight> delayMoreList=new ArrayList<>();
        for (Flight flight : allFlightList) {
            for (int i = 0; i < flight.getSegments().size()-1; i++) {
                Segment segment = flight.getSegments().get(i);
                if (segment.getArrivalDate()
                        .plusHours(hour)
                        .isBefore(flight.
                                getSegments()
                                .get(i + 1)
                                .getDepartureDate())) {
                    delayMoreList.add(flight);
                }
            }
        }
        return delayMoreList;
    }

    @Override
    public void flightPrint(List<Flight> allFlightList) {
        AtomicInteger index = new AtomicInteger(0);
        for (int i=0; i<allFlightList.size(); i++ ) {
            System.out.println("Перелёт №"+i);
            index.getAndSet(0);
            allFlightList.get(i).getSegments().forEach(segment->System.out.printf(
                    "Сегмент №%s  %nВылет %s  %nПрилёт %s%n",
                    index.getAndIncrement(),
                    segment.getDepartureDate(),
                    segment.getArrivalDate()));
        }
    }


}
