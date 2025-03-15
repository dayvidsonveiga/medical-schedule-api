package br.com.codart.application.usecases.schedule.addslot;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class AddSlotRequestDTO {

    private UUID scheduleId;

    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "08:00")
    private LocalTime startTime;

    private long duration;
}
