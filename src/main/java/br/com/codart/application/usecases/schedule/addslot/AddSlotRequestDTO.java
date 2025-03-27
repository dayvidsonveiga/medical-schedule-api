package br.com.codart.application.usecases.schedule.addslot;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSlotRequestDTO {

    private UUID scheduleId;

    @JsonFormat(pattern = "HH:mm")
    @Schema(example = "08:00")
    private LocalTime startTime;

    private long duration;
}
