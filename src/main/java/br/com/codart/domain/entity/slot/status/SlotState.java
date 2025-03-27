package br.com.codart.domain.entity.slot.status;

import br.com.codart.domain.entity.slot.Slot;
import br.com.codart.domain.entity.slot.SlotStatus;

public interface SlotState {

    void block(Slot slot);
    void cancel(Slot slot);
    void reserve(Slot slot);
    void reschedule(Slot slot);
    void reopen(Slot slot);
    SlotStatus getStatus();
}
