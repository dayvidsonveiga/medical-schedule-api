package br.com.codart.application.usecases.schedule.addslot;

import br.com.codart.domain.repository.DomainScheduleRepository;
import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.entity.slot.Slot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddSlot implements AddSlotUseCase{

    private final DomainScheduleRepository domainScheduleRepository;

    public AddSlotResponseDTO execute(AddSlotRequestDTO request) {
        Slot slot = Slot.create(request.getStartTime(), request.getDuration());

        Schedule schedule = domainScheduleRepository.findById(request.getScheduleId()).orElseThrow();

        schedule.addSlot(slot);

        domainScheduleRepository.save(schedule);

        return new AddSlotResponseDTO(slot.getId());
    }
}
