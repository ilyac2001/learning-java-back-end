package org.answer.filter;

import org.answer.filter.strategy.IFlightFilter;
import org.answer.model.Flight;

import java.util.List;

public class Filter {
    protected IFlightFilter flightFilterStrategy;
    protected List<Flight> flights;
    public Filter(List<Flight> flights, IFlightFilter flightFilter) {
        this.flights = flights;
        this.flightFilterStrategy = flightFilter;
    }
    public List<Flight> FilterFlight() {
        return this.flightFilterStrategy.Filter(this.flights);
    }

    public void SetFilterStrategy(IFlightFilter flightFilter) {
        this.flightFilterStrategy = flightFilter;
    }

    public static void PrintFlight(List<Flight> flightsFilter){
        for (Flight flight : flightsFilter) {
            System.out.println(flight);
        }
    }
}
