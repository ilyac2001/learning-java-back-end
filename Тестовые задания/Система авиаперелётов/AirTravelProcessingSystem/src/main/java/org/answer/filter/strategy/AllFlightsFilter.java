package org.answer.filter.strategy;

import org.answer.model.Flight;

import java.util.List;

public class AllFlightsFilter implements IFlightFilter {
    @Override
    public List<Flight> Filter(List<Flight> flights) {
        System.out.println("Original list of flights:");
        return flights;
    }
}
