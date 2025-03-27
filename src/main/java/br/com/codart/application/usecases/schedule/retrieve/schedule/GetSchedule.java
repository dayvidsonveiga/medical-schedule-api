package br.com.codart.application.usecases.schedule.retrieve.schedule;

import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.repository.DomainScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSchedule implements GetScheduleUseCase {

    private final DomainScheduleRepository domainScheduleRepository;

    @Override
    public GetScheduleResponseDTO execute(UUID scheduleId) {
        Schedule schedule = domainScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("schedule not found"));

        return OutputMapper.toOutput(schedule);
    }
}
