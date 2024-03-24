package org.answer.filter.strategy;

import org.answer.model.Flight;

import java.util.List;

public interface IFlightFilter {
    List<Flight> Filter(List<Flight> flights);
}
