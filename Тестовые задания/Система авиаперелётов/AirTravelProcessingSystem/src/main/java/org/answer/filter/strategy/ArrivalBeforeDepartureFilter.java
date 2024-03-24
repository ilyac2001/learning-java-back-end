package org.answer.filter.strategy;

import org.answer.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFilter implements IFlightFilter {
    @Override
    public List<Flight> Filter(List<Flight> flights) {
        System.out.println("Arrival before departure list of flights:");
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
