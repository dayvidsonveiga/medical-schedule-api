package br.com.codart.application.usecases.schedule.retrieve.schedule;

import java.util.UUID;

public interface GetScheduleUseCase {

    GetScheduleResponseDTO execute(UUID scheduleId);
}
