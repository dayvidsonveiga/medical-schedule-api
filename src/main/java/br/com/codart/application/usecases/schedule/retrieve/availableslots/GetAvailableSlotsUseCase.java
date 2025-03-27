package br.com.codart.application.usecases.schedule.retrieve.availableslots;

import java.util.List;
import java.util.UUID;

public interface GetAvailableSlotsUseCase {

    List<GetAvailableSlotsResponseDTO> execute(UUID scheduleId);
}
