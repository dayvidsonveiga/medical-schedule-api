package br.com.codart.application.usecases.schedule.retrieve.availableslots;

import br.com.codart.domain.repository.DomainScheduleRepository;
import br.com.codart.domain.entity.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAvailableSlots implements GetAvailableSlotsUseCase {

    private final DomainScheduleRepository domainScheduleRepository;

    @Override
    public List<GetAvailableSlotsResponseDTO> execute(UUID scheduleId) {
        Schedule schedule = domainScheduleRepository.getScheduleWithSlotStatusEqual(scheduleId, "AVAILABLE")
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        return OutputMapper.toOutput(scheduleId, schedule.getScheduleDate(), schedule.getSlots());
    }
}
