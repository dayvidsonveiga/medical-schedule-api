package br.com.codart.domain.entities.slot.status;

import br.com.codart.domain.entities.slot.Slot;
import br.com.codart.domain.entities.slot.SlotStatus;

import java.time.LocalTime;

public class RescheduledState implements SlotState {

    @Override
    public void block(Slot slot) {

    }

    @Override
    public void cancel(Slot slot) {

    }

    @Override
    public void reserve(Slot slot) {

    }

    @Override
    public void reschedule(Slot slot) {

    }

    @Override
    public void reopen(Slot slot) {

    }

    @Override
    public SlotStatus getStatus() {
        return SlotStatus.RESCHEDULED;
    }
}
