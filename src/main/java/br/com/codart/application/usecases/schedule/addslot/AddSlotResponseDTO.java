package br.com.codart.application.usecases.schedule.addslot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddSlotResponseDTO {

    private UUID slotId;
}
