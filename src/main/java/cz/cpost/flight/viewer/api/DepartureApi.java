package cz.cpost.flight.viewer.api;

import cz.cpost.flight.viewer.model.Departure;
import cz.cpost.flight.viewer.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * API controller for fetching flight departure data.
 * This controller handles HTTP GET requests to the /departures endpoint.
 * It also provides a form for user input.
 * This controller uses Thymeleaf for rendering HTML templates.
 */
@Controller
public class DepartureApi {

    @Autowired
    DepartureService departureService;

    @GetMapping("/departures-form")
    public String showForm() {
        return "departureForm"; // Thymeleaf template
    }

    @GetMapping("/departures")
    public String getDepartures(
            @RequestParam String airport,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime begin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Model model) {

        // Time range validation
        if (begin.isAfter(end) || begin.isEqual(end)) {
            return "error-invalid-time-range"; // Thymeleaf template
        }

        long secondsBetween = ChronoUnit.SECONDS.between(begin, end);
        if (secondsBetween > 604800) { // 7 days in seconds
            return "error-time-range-too-long"; // Thymeleaf template
        }


        // Slower variant
        /*List<Departure> departures = departureService.getDepartures(
                airport,
                String.valueOf(begin.atZone(ZoneId.of("Europe/Prague")).toEpochSecond()),
                String.valueOf(end.atZone(ZoneId.of("Europe/Prague")).toEpochSecond())
        );

        model.addAttribute("departures", departures);

        return "departures";*/

        // Faster variant
        Mono<List<Departure>> departures = departureService.getDepartures(
                airport,
                String.valueOf(begin.atZone(ZoneId.of("Europe/Prague")).toEpochSecond()),
                String.valueOf(end.atZone(ZoneId.of("Europe/Prague")).toEpochSecond())
        );

        model.addAttribute("departures", Objects.requireNonNull(departures.block()));

        model.addAttribute("departures", Objects.requireNonNull(departures.block()));
        model.addAttribute("airportName", airport);
        model.addAttribute("beginTime", begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        model.addAttribute("endTime", end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return "departures"; // Thymeleaf template
    }
}
