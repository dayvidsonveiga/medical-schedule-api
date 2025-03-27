package br.com.codart.application.usecases.schedule.retrieve.availableslots;

import br.com.codart.common.utility.DateTimeFormatterUtil;
import br.com.codart.domain.entity.slot.Slot;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

class OutputMapper {

    private OutputMapper() {}

    private static final DateTimeFormatterUtil FORMATTER = new DateTimeFormatterUtil();

    static List<GetAvailableSlotsResponseDTO> toOutput(UUID scheduleId, LocalDate scheduleDate, List<Slot> slots) {
        return slots.stream()
                .map(slot -> toOutput(scheduleId, scheduleDate, slot))
                .toList();
    }

    static GetAvailableSlotsResponseDTO toOutput(UUID scheduleId, LocalDate scheduleDate, Slot slot) {
        String dayDescription = FORMATTER.formatDay(scheduleDate);
        String timeDescription = FORMATTER.formatTime(slot.getStartTime(), slot.getEndTime());
        String duration = FORMATTER.calculateDuration(slot.getStartTime(), slot.getEndTime());

        return new GetAvailableSlotsResponseDTO(
                slot.getId(), scheduleId, dayDescription, timeDescription, duration, slot.getState().getStatus());
    }
}
