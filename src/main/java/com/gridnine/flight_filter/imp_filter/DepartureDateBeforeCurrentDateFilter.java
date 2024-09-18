package com.gridnine.flight_filter.imp_filter;

import com.gridnine.flight_filter.FlightFilter;
import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;


// Фильтр исключает рейсы с вылетом до текущего момента времени
public class DepartureDateBeforeCurrentDateFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(now)))
                .toList();


    }
}
