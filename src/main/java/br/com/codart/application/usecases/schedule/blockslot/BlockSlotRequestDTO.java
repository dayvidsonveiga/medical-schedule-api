package br.com.codart.application.usecases.schedule.blockslot;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockSlotRequestDTO {

    @Hidden
    private UUID scheduleId;

    @Hidden
    private UUID slotId;
}
