package br.com.codart.domain.entities.slot.states;

import br.com.codart.domain.entities.slot.Slot;

import java.time.LocalTime;

public interface SlotState {

    void block(Slot slot);
    void cancel(Slot slot);
    void reserve(Slot slot);
    void reschedule(Slot slot, LocalTime newStartTime, long durationMinutes);
    void reopen(Slot slot);
}
