package br.com.codart.domain.entity.slot.status;

import br.com.codart.domain.entity.slot.Slot;
import br.com.codart.domain.entity.slot.SlotStatus;

public class CancelledState implements SlotState {

    @Override
    public void reopen(Slot slot) {
        slot.reopen();
    }

    @Override
    public void block(Slot slot) {
        throw new IllegalStateException("Slot não pode ser bloqueado, pois está cancelado");
    }

    @Override
    public void cancel(Slot slot) {
        throw new IllegalStateException("Slot já está cancelado");
    }

    @Override
    public void reserve(Slot slot) {
        throw new IllegalStateException("Slot não pode ser reservado, pois está cancelado");
    }

    @Override
    public void reschedule(Slot slot) {
        throw new IllegalStateException("Slot não pode ser reagendado, pois está cancelado");
    }

    @Override
    public SlotStatus getStatus() {
        return SlotStatus.CANCELLED;
    }
}
