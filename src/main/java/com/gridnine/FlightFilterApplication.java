package com.gridnine;

import com.gridnine.flight_filter.FlightFilter;
import com.gridnine.flight_filter.imp_filter.ArrivalDateBeforeDepartureDateFilter;
import com.gridnine.flight_filter.imp_filter.DepartureDateBeforeCurrentDateFilter;
import com.gridnine.flight_filter.imp_filter.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class FlightFilterApplication {

    public static void main(String[] args) {
        //SpringApplication.run(FlightFilterApplication.class, args);
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Все полёты [Дата вылета Т время вылета | Дата прилета Т время прилета]:");
        flights.forEach(System.out::println);

        LocalDateTime now = LocalDateTime.now();
        FlightFilter departureDateBeforeCurrentDateFilter = new DepartureDateBeforeCurrentDateFilter();
        FlightFilter arrivalDateBeforeDepartureDateFilter = new ArrivalDateBeforeDepartureDateFilter();
        FlightFilter groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter();


        System.out.println("\nРейсы после текущей даты и времени: " + now);
        departureDateBeforeCurrentDateFilter.filter(flights).forEach(System.out::println);

        System.out.println("\nРейсы, в которых дата прилета позже даты вылета:");
        arrivalDateBeforeDepartureDateFilter.filter(flights).forEach(System.out::println);

        System.out.println("\nРейсы, где общее время, проведённое на земле, не превышает 2 часа");
        groundTimeExceedsTwoHoursFilter.filter(flights).forEach(System.out::println);


    }

}
