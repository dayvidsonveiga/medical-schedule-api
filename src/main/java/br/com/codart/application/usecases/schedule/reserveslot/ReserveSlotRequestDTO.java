package br.com.codart.application.usecases.schedule.reserveslot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveSlotRequestDTO {

    private UUID scheduleId;
    private UUID slotId;
}
