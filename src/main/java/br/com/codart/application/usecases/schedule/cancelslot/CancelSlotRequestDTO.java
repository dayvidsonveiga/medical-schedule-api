package br.com.codart.application.usecases.schedule.cancelslot;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelSlotRequestDTO {

    @Hidden
    private UUID scheduleId;

    @Hidden
    private UUID slotId;
}
