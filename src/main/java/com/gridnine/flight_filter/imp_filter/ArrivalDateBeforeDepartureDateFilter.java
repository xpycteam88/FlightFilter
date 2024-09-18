package com.gridnine.flight_filter.imp_filter;

import com.gridnine.flight_filter.FlightFilter;
import com.gridnine.testing.Flight;

import java.util.List;

// Фильтр исключает сегменты, в которых дата прилета раньше даты вылета
public class ArrivalDateBeforeDepartureDateFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .toList();
    }
}
