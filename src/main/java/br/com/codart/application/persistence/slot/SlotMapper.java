package br.com.codart.application.persistence.slot;


import br.com.codart.domain.entities.slot.Slot;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {

    public SlotEntity toEntity(Slot domain) {
        if (domain == null) {
            return null;
        }

        SlotEntity entity = new SlotEntity();
        entity.setId(domain.getSlotId());
        entity.setStartTime(domain.getStartTime());
        entity.setEndTime(domain.getEndTime());
        entity.setStatus(domain.getState().getStatus());
        return entity;
    }

    public Slot toDomain(SlotEntity entity) {
        if (entity == null) {
            return null;
        }

        return Slot.recreate(
                entity.getId(), entity.getStartTime(), entity.getEndTime(), entity.getStatus());
    }
}
