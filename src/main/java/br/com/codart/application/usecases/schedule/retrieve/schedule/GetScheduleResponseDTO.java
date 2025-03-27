package br.com.codart.application.usecases.schedule.retrieve.schedule;

import br.com.codart.domain.entity.schedule.ScheduleStatus;
import br.com.codart.domain.entity.slot.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetScheduleResponseDTO {

    private UUID scheduleId;
    private LocalDate scheduleDate;
    private ScheduleStatus status;
    private List<GetSlotResponseDTO> times;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetSlotResponseDTO {

        private UUID slotId;
        private String startTime;
        private String endTime;
        private SlotStatus status;
    }
}
