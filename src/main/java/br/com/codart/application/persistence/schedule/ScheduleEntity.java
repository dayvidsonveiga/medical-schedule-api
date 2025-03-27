package br.com.codart.application.persistence.schedule;


import br.com.codart.application.persistence.slot.SlotEntity;
import br.com.codart.domain.entity.schedule.ScheduleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "schedule_sample")
public class ScheduleEntity {

    @Id
    private UUID id;

    private LocalDate scheduleDate;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @OneToMany(
            mappedBy = "schedule",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<SlotEntity> slots = new ArrayList<>();

    public void addSlot(SlotEntity slot) {
        slots.add(slot);
        slot.setSchedule(this);
    }
}
