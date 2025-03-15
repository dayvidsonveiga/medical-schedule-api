package br.com.codart.domain.entities.schedule;

import br.com.codart.domain.entities.slot.Slot;
import br.com.codart.domain.entities.slot.SlotStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Schedule {

    private final UUID id;
    private final LocalDate scheduleDate;
    private ScheduleStatus status;
    private final List<Slot> slots;

    private Schedule(UUID id, LocalDate scheduleDate, ScheduleStatus status, List<Slot> slots) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.status = (status != null) ? status : ScheduleStatus.ACTIVE;
        this.slots = (slots != null) ? new ArrayList<>(slots) : new ArrayList<>();

        selfValidation();
    }

    /**
     * Cria uma nova agenda aberta com horários disponíveis.
     *
     * @param scheduleDate   Data da agenda.
     * @param availableSlots Lista de horários disponíveis.
     * @return Uma nova instância de Schedule.
     * @throws IllegalArgumentException Se a data ou os availableSlots fornecidos forem inválidos.
     */
    public static Schedule create(LocalDate scheduleDate, List<Slot> availableSlots) {
        return new Schedule(UUID.randomUUID(), scheduleDate, ScheduleStatus.ACTIVE, availableSlots);
    }

    public void addSlot(Slot slot) {
        validateNoOverlap(null, slot.getStartTime(), slot.getEndTime());

        this.slots.add(slot);

        calculateStatus();
    }

    public void blockSlot(Slot slot) {
        findSlot(slot.getSlotId());
        slot.block();
        calculateStatus();
    }

    public void cancelSlot(Slot slot) {
        findSlot(slot.getSlotId());
        slot.cancel();
        calculateStatus();
    }

    public void rescheduleSlot(Slot slot, LocalTime newDate, long durationMinutes) {
        findSlot(slot.getSlotId());
        slot.reschedule();
        Slot newSlot = Slot.create(newDate, durationMinutes);
        this.slots.add(newSlot);
        calculateStatus();
    }

    private void selfValidation() {
        if (scheduleDate == null) {
            throw new IllegalArgumentException("scheduleDate não pode ser nulo");
        }

        if (id == null) {
            throw new IllegalArgumentException("id não pode ser nulo");
        }

        validateSlotConflicts();
    }

    private Slot findSlot(UUID slotId) {
        return this.slots.stream()
                .filter(slot -> slot.getSlotId().equals(slotId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Slot nao encontrado na agenda"));
    }

    private void validateSlotConflicts(Slot newSlot) {
        for (Slot existingSlot : slots) {
            if ((newSlot.getStartTime().isBefore(existingSlot.getEndTime()) &&
                    newSlot.getEndTime().isAfter(existingSlot.getStartTime()))) {
                throw new IllegalArgumentException("Conflito de horário entre os slots");
            }
        }
    }

    private void validateSlotConflicts() {
        for (int i = 0; i < slots.size(); i++) {
            for (int j = i + 1; j < slots.size(); j++) {
                Slot slot1 = slots.get(i);
                Slot slot2 = slots.get(j);
                if (slot1.getStartTime().isBefore(slot2.getEndTime()) &&
                        slot1.getEndTime().isAfter(slot2.getStartTime())) {
                    throw new IllegalArgumentException("Conflito de horário entre os slots");
                }
            }
        }
    }

    private void validateNoOverlap(UUID excludedSlotId, LocalTime startTime, LocalTime endTime) {
        for (Slot existingSlot : this.slots) {
            if (existingSlot.getSlotId().equals(excludedSlotId)) {
                continue;
            }
            if (isOverlapping(existingSlot, startTime, endTime)) {
                throw new IllegalArgumentException("O horário fornecido se sobrepõe a outro horário existente na agenda.");
            }
        }
    }

    private boolean isOverlapping(Slot slot, LocalTime startTime, LocalTime endTime) {
        return !(slot.getEndTime().isBefore(startTime) || slot.getStartTime().isAfter(endTime));
    }

    private void calculateStatus() {
        if (slots.isEmpty()) {
            this.status = ScheduleStatus.ACTIVE;
        } else {
            boolean isFullyBooked = true;
            for (Slot slot : slots) {
                if (slot.isAvailable()) {
                    isFullyBooked = false;
                    break;
                }
            }
            this.status = isFullyBooked ? ScheduleStatus.FULLY_BOOKED : ScheduleStatus.ACTIVE;
        }
    }
}
