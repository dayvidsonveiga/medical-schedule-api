package br.com.codart.domain.entities.slot;

import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
public class Slot {

    private final UUID slotId;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private SlotStatus status;

    private Slot(UUID slotId, LocalTime startTime, LocalTime endTime, SlotStatus status) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        selfValidation();
    }

    public static Slot create(LocalTime startTime, long durationMinutes) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duração do slot deve ser positiva");
        }

        LocalTime endTime = startTime.plusMinutes(durationMinutes);

        return new Slot(UUID.randomUUID(), startTime, endTime, SlotStatus.AVAILABLE);
    }

    public static Slot recreate(UUID slotId, LocalTime startTime, LocalTime endTime, SlotStatus slotStatus) {
        return new Slot(slotId, startTime, endTime, slotStatus);
    }

    public Slot block() {
        if (this.status == SlotStatus.RESERVED || this.status == SlotStatus.CANCELLED || this.status == SlotStatus.RESCHEDULED) {
            throw new IllegalStateException("Slot não pode ser bloqueado, pois está reservado, cancelado ou reagendado");
        }

        this.status = SlotStatus.BLOCKED;

        return this;
    }

    public Slot cancel() {
        if (this.status == SlotStatus.BLOCKED || this.status == SlotStatus.AVAILABLE) {
            throw new IllegalStateException("Slot não pode ser cancelado, pois está disponível ou bloqueado");
        }

        this.status = SlotStatus.CANCELLED;

        return this;
    }

    public Slot reserve() {
        if (this.status != SlotStatus.AVAILABLE) {
            throw new IllegalStateException("Slot não disponível para reserva");
        }

        this.status = SlotStatus.RESERVED;

        return this;
    }

    public Slot reschedule(LocalTime newStartTime, long durationMinutes) {
        if (this.status != SlotStatus.RESERVED) {
            throw new IllegalStateException("Somente slots reservados podem ser reagendados");
        }

        if (newStartTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Novo horário de início não pode ser no passado");
        }

        LocalTime newEndTime = newStartTime.plusMinutes(durationMinutes);

        return new Slot(this.slotId, newStartTime, newEndTime, SlotStatus.RESCHEDULED);
    }

    public Slot reopen() {
        if (this.status != SlotStatus.CANCELLED && this.status != SlotStatus.BLOCKED) {
            throw new IllegalStateException("Somente slots cancelados ou bloqueados podem ser reabertos");
        }

        this.status = SlotStatus.AVAILABLE;

        return this;
    }

    private void selfValidation() {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("endTime não pode ser anterior a startTime");
        }

        if (slotId == null) {
            throw new IllegalArgumentException("slotId não pode ser nulo");
        }

        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("startTime e endTime não podem ser nulos");
        }

        if (startTime.equals(endTime)) {
            throw new IllegalArgumentException("startTime não pode ser igual a endTime");
        }

        if (status == null) {
            throw new IllegalArgumentException("slotStatus não pode ser nulo");
        }
    }

    public boolean isAvailable() {
        return this.status == SlotStatus.AVAILABLE;
    }
}
