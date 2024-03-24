package org.answer.filter.strategy;

import org.answer.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeNowFilter implements IFlightFilter {
    @Override
    public List<Flight> Filter(List<Flight> flights) {
        System.out.println("Departure before now list of flights:");
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(now)))
                .collect(Collectors.toList());
    }
}
