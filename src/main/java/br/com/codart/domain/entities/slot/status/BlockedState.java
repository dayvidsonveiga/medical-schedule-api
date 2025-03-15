package br.com.codart.domain.entities.slot.status;

import br.com.codart.domain.entities.slot.Slot;
import br.com.codart.domain.entities.slot.SlotStatus;

import java.time.LocalTime;

public class BlockedState implements SlotState {


    @Override
    public void reopen(Slot slot) {
        slot.reopen();
    }

    @Override
    public void cancel(Slot slot) {
        slot.cancel();
    }

    @Override
    public void block(Slot slot) {
        throw new IllegalStateException("Slot já está bloqueado");
    }

    @Override
    public void reserve(Slot slot) {
        throw new IllegalStateException("Slot não pode ser reservado, pois está bloqueado");
    }

    @Override
    public void reschedule(Slot slot) {
        throw new IllegalStateException("Slot não pode ser reagendado, pois está bloqueado");
    }

    @Override
    public SlotStatus getStatus() {
        return SlotStatus.BLOCKED;
    }
}
