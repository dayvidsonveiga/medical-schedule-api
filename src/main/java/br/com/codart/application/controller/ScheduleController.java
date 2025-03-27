package br.com.codart.application.controller;

import br.com.codart.application.controller.docs.ScheduleAPI;
import br.com.codart.application.usecases.schedule.addslot.AddSlotRequestDTO;
import br.com.codart.application.usecases.schedule.addslot.AddSlotResponseDTO;
import br.com.codart.application.usecases.schedule.addslot.AddSlotUseCase;
import br.com.codart.application.usecases.schedule.blockslot.BlockSlotRequestDTO;
import br.com.codart.application.usecases.schedule.blockslot.BlockSlotUseCase;
import br.com.codart.application.usecases.schedule.cancelslot.CancelSlotRequestDTO;
import br.com.codart.application.usecases.schedule.cancelslot.CancelSlotUseCase;
import br.com.codart.application.usecases.schedule.createschedule.CreateScheduleRequestDTO;
import br.com.codart.application.usecases.schedule.createschedule.CreateScheduleResponseDTO;
import br.com.codart.application.usecases.schedule.createschedule.CreateScheduleUseCase;
import br.com.codart.application.usecases.schedule.reopenslot.OpenSlotRequestDTO;
import br.com.codart.application.usecases.schedule.reopenslot.ReopenSlotUseCase;
import br.com.codart.application.usecases.schedule.reserveslot.ReserveSlotRequestDTO;
import br.com.codart.application.usecases.schedule.reserveslot.ReserveSlotUseCase;
import br.com.codart.application.usecases.schedule.retrieve.availableslots.GetAvailableSlotsResponseDTO;
import br.com.codart.application.usecases.schedule.retrieve.availableslots.GetAvailableSlotsUseCase;
import br.com.codart.application.usecases.schedule.retrieve.schedule.GetScheduleResponseDTO;
import br.com.codart.application.usecases.schedule.retrieve.schedule.GetScheduleUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
@Tags(value = @Tag(name = "Schedule", description = "Endpoints relacionados à gestão de agendamentos."))
public class ScheduleController implements ScheduleAPI {

    private final AddSlotUseCase addSlotUseCase;
    private final BlockSlotUseCase blockSlotUseCase;
    private final CancelSlotUseCase cancelSlotUseCase;
    private final ReopenSlotUseCase reopenSlotUseCase;
    private final GetScheduleUseCase getScheduleUseCase;
    private final ReserveSlotUseCase reserveSlotUseCase;
    private final CreateScheduleUseCase createScheduleUseCase;
    private final GetAvailableSlotsUseCase getAvailableSlotsUseCase;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleResponseDTO> getSchedule(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getScheduleUseCase.execute(id));
    }

    @Override
    @GetMapping("/{id}/available-slots")
    public ResponseEntity<List<GetAvailableSlotsResponseDTO>> getAvailableSlots(
            @PathVariable("id") UUID id) {
        return ResponseEntity.ok(getAvailableSlotsUseCase.execute(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<CreateScheduleResponseDTO> createSchedule(@RequestBody CreateScheduleRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createScheduleUseCase.execute(request));
    }

    @Override
    @PostMapping("/{id}/add-slot")
    public ResponseEntity<AddSlotResponseDTO> addSlotToSchedule(@PathVariable("id") UUID id,
                                                                @RequestBody AddSlotRequestDTO request) {
        request.setScheduleId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(addSlotUseCase.execute(request));
    }

    @Override
    @PatchMapping("/{id}/block-slot/{slotId}")
    public ResponseEntity<Void> blockSlotFromSchedule(@PathVariable("id") UUID id,
                                                      @PathVariable("slotId") UUID slotId) {
        blockSlotUseCase.execute(new BlockSlotRequestDTO(id, slotId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PatchMapping("/{id}/cancel-slot/{slotId}")
    public ResponseEntity<Void> cancelSlotFromSchedule(@PathVariable("id") UUID id,
                                                       @PathVariable("slotId") UUID slotId) {
        cancelSlotUseCase.execute(new CancelSlotRequestDTO(id, slotId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PatchMapping("/{id}/reopen-slot/{slotId}")
    public ResponseEntity<Void> openSlotFromSchedule(@PathVariable("id") UUID id,
                                                     @PathVariable("slotId") UUID slotId) {
        reopenSlotUseCase.execute(new OpenSlotRequestDTO(id, slotId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PatchMapping("/{id}/reserve-slot/{slotId}")
    public ResponseEntity<Void> reserveSlotFromSchedule(@PathVariable("id") UUID id,
                                                        @PathVariable("slotId") UUID slotId) {
        reserveSlotUseCase.execute(new ReserveSlotRequestDTO(id, slotId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
