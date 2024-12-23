package br.com.codart.domain.entities.slot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SlotTest {

    private Slot slot;

    @BeforeEach
    public void setUp() {
        slot = Slot.create(LocalTime.of(10, 0), 60);
    }

    @Test
    void testCreateSlot() {
        assertNotNull(slot);
        assertEquals(SlotStatus.AVAILABLE, slot.getSlotStatus());
        assertEquals(LocalTime.of(10, 0), slot.getStartTime());
        assertEquals(LocalTime.of(11, 0), slot.getEndTime());
    }

    @Test
    void testCreateSlotWithNegativeDuration() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Slot.create(LocalTime.of(10, 0), -10);
        });
        assertEquals("Duração do slot deve ser positiva", exception.getMessage());
    }

    @Test
    void testBlockSlot() {
        slot.block();
        assertEquals(SlotStatus.BLOCKED, slot.getSlotStatus());
    }

    @Test
    void testBlockReservedSlot() {
        slot.reserve();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            slot.block();
        });
        assertEquals("Slot não pode ser bloqueado, pois está reservado, cancelado ou reagendado", exception.getMessage());
    }

    @Test
    void testCancelSlot() {
        slot.reserve();
        slot.cancel();
        assertEquals(SlotStatus.CANCELLED, slot.getSlotStatus());
    }

    @Test
    void testCancelAvailableSlot() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            slot.cancel();
        });
        assertEquals("Slot não pode ser cancelado, pois está disponível ou bloqueado", exception.getMessage());
    }

    @Test
    void testReserveAvailableSlot() {
        slot.reserve();
        assertEquals(SlotStatus.RESERVED, slot.getSlotStatus());
    }

    @Test
    void testReserveBlockedSlot() {
        slot.block();
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            slot.reserve();
        });
        assertEquals("Slot não disponível para reserva", exception.getMessage());
    }

    @Test
    void testRescheduleReservedSlot() {
        slot.reserve();
        LocalTime newStartTime = LocalTime.now().plusMinutes(90);
        Slot rescheduledSlot = slot.reschedule(newStartTime, 30);

        assertNotNull(rescheduledSlot);
        assertEquals(newStartTime, rescheduledSlot.getStartTime());
        assertEquals(SlotStatus.RESCHEDULED, rescheduledSlot.getSlotStatus());
    }


    @Test
    void testRescheduleNonReservedSlot() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            slot.reschedule(LocalTime.of(12, 0), 30);
        });
        assertEquals("Somente slots reservados podem ser reagendados", exception.getMessage());
    }

    @Test
    void testReopenCancelledSlot() {
        slot.reserve();
        slot.cancel();
        slot.reopen();

        assertEquals(SlotStatus.AVAILABLE, slot.getSlotStatus());
    }

    @Test
    void testReopenAvailableSlot() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            slot.reopen();
        });
        assertEquals("Somente slots cancelados ou bloqueados podem ser reabertos", exception.getMessage());
    }
}
