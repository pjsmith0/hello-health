package pjs.entrypoints;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pjs.core.visit.VisitService;
import pjs.model.visit.dto.CreateVisitDTO;
import pjs.model.visit.dto.UpdateVisitDTO;
import pjs.model.visit.dto.VisitDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Path("/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public VisitDTO create(CreateVisitDTO createVisitDTO) {
        return visitService.create(createVisitDTO);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public VisitDTO update(UpdateVisitDTO updateVisitDTO) {
        return visitService.update(updateVisitDTO);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{visitId}")
    public Response delete(UUID visitId) {
        visitService.delete(visitId);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VisitDTO> list(String date) {
        return visitService.list(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
    }

}
