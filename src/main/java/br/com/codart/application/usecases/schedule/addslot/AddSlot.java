package br.com.codart.application.usecases.schedule.addslot;

import br.com.codart.domain.entities.repository.DomainScheduleRepository;
import br.com.codart.domain.entities.schedule.Schedule;
import br.com.codart.domain.entities.slot.Slot;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddSlot {

    private final DomainScheduleRepository domainScheduleRepository;

    public AddSlotResponseDTO process(AddSlotRequestDTO request) {
        Slot slot = Slot.create(request.getStartTime(), request.getDuration());

        Schedule schedule = domainScheduleRepository.findById(request.getScheduleId()).orElseThrow();

        schedule.addSlot(slot);

        domainScheduleRepository.save(schedule);

        return new AddSlotResponseDTO(slot.getSlotId());
    }
}
