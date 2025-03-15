package br.com.codart.domain.entities.slot;

import br.com.codart.domain.entities.slot.status.*;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
public class Slot {

    private final UUID slotId;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private SlotState state;

    private Slot(UUID slotId, LocalTime startTime, LocalTime endTime) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = new AvailableState();
        selfValidation();
    }

    void transitionTo(SlotState newState) {
        this.state = newState;
    }

    private static SlotState stateFromStatus(SlotStatus slotStatus) {
        return switch (slotStatus) {
            case AVAILABLE -> new AvailableState();
            case BLOCKED -> new BlockedState();
            case RESERVED -> new ReservedState();
            case CANCELLED -> new CancelledState();
            case RESCHEDULED -> new RescheduledState();
        };
    }

    public static Slot create(LocalTime startTime, long durationMinutes) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duração do slot deve ser positiva");
        }

        LocalTime endTime = startTime.plusMinutes(durationMinutes);

        return new Slot(UUID.randomUUID(), startTime, endTime);
    }

    public static Slot recreate(UUID slotId, LocalTime startTime, LocalTime endTime, SlotStatus status) {
        Slot slot = new Slot(slotId, startTime, endTime);
        slot.transitionTo(stateFromStatus(status));
        return slot;
    }

    public void block() {
        state.block(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void reserve() {
        state.reserve(this);
    }

    public void reschedule() {
        state.reschedule(this);
    }

    public void reopen() {
        state.reopen(this);
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

        if (state == null) {
            throw new IllegalArgumentException("slotStatus não pode ser nulo");
        }
    }

    public boolean isAvailable() {
        return this.state.getStatus() == SlotStatus.AVAILABLE;
    }
}
