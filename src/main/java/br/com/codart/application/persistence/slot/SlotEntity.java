package br.com.codart.application.persistence.slot;


import br.com.codart.application.persistence.schedule.ScheduleEntity;
import br.com.codart.domain.entity.slot.SlotStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "slot_sample")
public class SlotEntity {

    @Id
    private UUID id;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable =    false)
    private ScheduleEntity schedule;
}
