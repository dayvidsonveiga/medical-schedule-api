package br.com.codart.domain.entities.slot.status;

import br.com.codart.domain.entities.slot.Slot;
import br.com.codart.domain.entities.slot.SlotStatus;

import java.time.LocalTime;

public interface SlotState {

    void block(Slot slot);
    void cancel(Slot slot);
    void reserve(Slot slot);
    void reschedule(Slot slot);
    void reopen(Slot slot);
    SlotStatus getStatus();
}
