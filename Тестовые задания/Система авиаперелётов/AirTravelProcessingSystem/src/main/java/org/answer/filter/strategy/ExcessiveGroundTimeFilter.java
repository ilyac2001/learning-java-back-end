package org.answer.filter.strategy;

import org.answer.model.Flight;
import org.answer.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ExcessiveGroundTimeFilter implements IFlightFilter {
    @Override
    public List<Flight> Filter(List<Flight> flights) {
        System.out.println("Excessive ground time list of flights:");
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalGroundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        LocalDateTime arrival = segments.get(i).getArrivalDate();
                        LocalDateTime departure = segments.get(i + 1).getDepartureDate();
                        totalGroundTime += Duration.between(arrival, departure).toHours();
                    }
                    return totalGroundTime <= 2;
                })
                .collect(Collectors.toList());
    }
}
