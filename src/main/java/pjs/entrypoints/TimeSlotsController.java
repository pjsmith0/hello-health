package pjs.entrypoints;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import pjs.core.timeslot.TimeSlotService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/timeslots")
public class TimeSlotsController {

    private final TimeSlotService timeSlotService;

    public TimeSlotsController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void generate(@QueryParam("date") String dateString) {
        timeSlotService.generateTimeSlots(LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE));
    }

}
