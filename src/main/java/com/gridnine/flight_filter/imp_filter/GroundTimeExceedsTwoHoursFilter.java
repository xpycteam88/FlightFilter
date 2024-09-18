package com.gridnine.flight_filter.imp_filter;

import com.gridnine.flight_filter.FlightFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

// Фильтр исключает рейсы, где время на земле между перелетами в сумме составляет больше 2 часов
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() == 1) {
                        return true;
                    }
                    int totalGroundTimeInMinutes = 0;
                    for (int i = 1; i < segments.size(); i++) {
                        Duration groundTime = Duration.between(
                                segments.get(i - 1).getArrivalDate(),
                                segments.get(i).getDepartureDate()
                        );
                        totalGroundTimeInMinutes += groundTime.toMinutes();
                    }
                    return totalGroundTimeInMinutes <= 120;
                }).collect(Collectors.toList());
    }
}
