package br.com.codart.application.usecases.schedule.reopenslot;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenSlotRequestDTO {

    private UUID scheduleId;
    private UUID slotId;
}
