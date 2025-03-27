package br.com.codart.application.usecases.schedule.createschedule;

import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.entity.slot.Slot;
import br.com.codart.domain.repository.DomainScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateSchedule implements CreateScheduleUseCase{

    private final DomainScheduleRepository domainScheduleRepository;

    public CreateScheduleResponseDTO execute(CreateScheduleRequestDTO request) {
        List<Slot> slots = new ArrayList<>();

        if (!request.getSlots().isEmpty()) {
            slots = request.getSlots().stream()
                    .map(s -> Slot.create(s.getStartTime(), s.getDuration()))
                    .toList();
        }

        Schedule schedule = Schedule.create(request.getDate(), slots);

        domainScheduleRepository.save(schedule);

        return new CreateScheduleResponseDTO(schedule.getId());
    }
}
