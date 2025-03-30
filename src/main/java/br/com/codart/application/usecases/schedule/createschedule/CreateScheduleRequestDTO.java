package br.com.codart.application.usecases.schedule.createschedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduleRequestDTO {

    private LocalDate date;
    private List<CreateSlotRequestDTO> slots;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateSlotRequestDTO {

        @JsonFormat(pattern = "HH:mm")
        @Schema(example = "08:00")
        LocalTime startTime;
        private long duration;
    }
}
