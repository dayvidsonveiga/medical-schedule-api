package br.com.codart.domain.entity.slot.status;

import br.com.codart.domain.entity.slot.Slot;
import br.com.codart.domain.entity.slot.SlotStatus;

public class ReservedState implements SlotState {

    @Override
    public void cancel(Slot slot) {
        slot.cancel();
    }

    @Override
    public void reschedule(Slot slot) {
        slot.reschedule();
    }

    @Override
    public void reopen(Slot slot) {
        throw new IllegalStateException("Slot não pode ser reaberto");
    }

    @Override
    public void block(Slot slot) {
        throw new IllegalStateException("Slot não pode ser bloqueado, pois está reservado");
    }

    @Override
    public void reserve(Slot slot) {
        throw new IllegalStateException("Slot já está reservado");
    }

    @Override
    public SlotStatus getStatus() {
        return SlotStatus.RESERVED;
    }
}
