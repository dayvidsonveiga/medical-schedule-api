package br.com.codart.application.persistence.schedule;

import br.com.codart.application.persistence.slot.SlotMapper;

import br.com.codart.domain.entity.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final SlotMapper slotMapper;

    public ScheduleEntity toEntity(Schedule domain) {
        if (domain == null) {
            return null;
        }

        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(domain.getId());
        entity.setScheduleDate(domain.getScheduleDate());
        entity.setStatus(domain.getStatus());

        domain.getSlots().forEach(slot -> entity.addSlot(slotMapper.toEntity(slot)));

        return entity;
    }

    public Schedule toDomain(ScheduleEntity entity) {
        if (entity == null) {
            return null;
        }

//        return Schedule.recreate(entity.getId(), entity.getScheduleDate(), mapTo(entity.getSlots(), slotMapper::toDomain));
        return null;
    }
}
