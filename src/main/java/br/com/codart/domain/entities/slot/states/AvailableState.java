package br.com.codart.domain.entities.slot.states;

import br.com.codart.domain.entities.slot.Slot;

import java.time.LocalTime;

public class AvailableState implements SlotState {

    @Override
    public void block(Slot slot) {
        slot.block();
    }

    @Override
    public void reserve(Slot slot) {
        slot.reserve();
    }

    @Override
    public void cancel(Slot slot) {
        throw new IllegalStateException("Slot disponível não pode ser cancelado.");
    }

    @Override
    public void reschedule(Slot slot, LocalTime newStartTime, long durationMinutes) {
        throw new IllegalStateException("Slot disponível não pode ser reagendado.");
    }

    @Override
    public void reopen(Slot slot) {
        throw new IllegalStateException("Slot disponível não pode ser reaberto.");
    }
}
