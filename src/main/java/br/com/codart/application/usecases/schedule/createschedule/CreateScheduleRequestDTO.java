package br.com.codart.application.usecases.schedule.createschedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class CreateScheduleRequestDTO {

    private LocalDate date;
    private List<CreateSlotRequestDTO> slots;

    @Data
    @Builder
    public class CreateSlotRequestDTO {

        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "08:00")
        LocalTime startTime;
        private long duration;
    }
}
