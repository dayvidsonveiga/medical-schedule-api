package br.com.codart.application.usecases.schedule.retrieve.availableslots;

import br.com.codart.domain.entity.slot.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvailableSlotsResponseDTO {

    private UUID slotId;
    private UUID scheduleId;
    private String dayDescription;
    private String timeDescription;
    private String duration;
    private SlotStatus status;
}
