package br.com.codart.domain.entities.slot;

import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
public class Slot {

    private final UUID slotId;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private SlotStatus slotStatus;

    private Slot(UUID slotId, LocalTime startTime, LocalTime endTime, SlotStatus slotStatus) {
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("endTime não pode ser anterior a startTime");
        }

        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotStatus = slotStatus;
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
        if (this.slotStatus == SlotStatus.RESERVED || this.slotStatus == SlotStatus.CANCELLED || this.slotStatus == SlotStatus.RESCHEDULED) {
            throw new IllegalStateException("Slot não pode ser bloqueado, pois está reservado, cancelado ou reagendado");
        }

        this.slotStatus = SlotStatus.BLOCKED;

        return this;
    }

    public Slot cancel() {
        if (this.slotStatus == SlotStatus.BLOCKED || this.slotStatus == SlotStatus.AVAILABLE) {
            throw new IllegalStateException("Slot não pode ser cancelado, pois está disponível ou bloqueado");
        }

        this.slotStatus = SlotStatus.CANCELLED;

        return this;
    }

    public Slot reserve() {
        if (this.slotStatus != SlotStatus.AVAILABLE) {
            throw new IllegalStateException("Slot não disponível para reserva");
        }

        this.slotStatus = SlotStatus.RESERVED;

        return this;
    }

    public Slot reschedule(LocalTime newStartTime, long durationMinutes) {
        if (this.slotStatus != SlotStatus.RESERVED) {
            throw new IllegalStateException("Somente slots reservados podem ser reagendados");
        }

        if (newStartTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Novo horário de início não pode ser no passado");
        }

        LocalTime newEndTime = newStartTime.plusMinutes(durationMinutes);

        return new Slot(this.slotId, newStartTime, newEndTime, SlotStatus.RESCHEDULED);
    }

    public Slot reopen() {
        if (this.slotStatus != SlotStatus.CANCELLED && this.slotStatus != SlotStatus.BLOCKED) {
            throw new IllegalStateException("Somente slots cancelados ou bloqueados podem ser reabertos");
        }

        this.slotStatus = SlotStatus.AVAILABLE;

        return this;
    }

    private void selfValidation() {
        if (slotId == null) {
            throw new IllegalArgumentException("slotId não pode ser nulo");
        }

        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("startTime e endTime não podem ser nulos");
        }

        if (startTime.equals(endTime)) {
            throw new IllegalArgumentException("startTime não pode ser igual a endTime");
        }

        if (slotStatus == null) {
            throw new IllegalArgumentException("slotStatus não pode ser nulo");
        }
    }
}
