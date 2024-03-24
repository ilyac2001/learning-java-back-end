package org.answer.util.main;

import org.answer.filter.Filter;
import org.answer.filter.strategy.*;
import org.answer.model.Flight;
import org.answer.util.factories.FlightBuilder;

import java.util.List;

import static org.answer.filter.Filter.PrintFlight;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        Filter filter = new Filter(flights, new AllFlightsFilter());
        PrintFlight(filter.FilterFlight());

        IFlightFilter[] flightFilters = {new ArrivalBeforeDepartureFilter(), new DepartureBeforeNowFilter(), new ExcessiveGroundTimeFilter()};
        for (IFlightFilter flightFilter : flightFilters){
            filter.SetFilterStrategy(flightFilter);
            PrintFlight(filter.FilterFlight());
        }
    }
}