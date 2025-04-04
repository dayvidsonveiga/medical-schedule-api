//package br.com.codart.domain.entity.slot;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SlotTest {
//
//    private Slot slot;
//
//    @BeforeEach
//    public void setUp() {
//        slot = Slot.create(LocalTime.of(10, 0), 60);
//    }
//
//    @Test
//    void testCreateSlot() {
//        assertNotNull(slot);
//        assertEquals(SlotStatus.AVAILABLE, slot.getState());
//        assertEquals(LocalTime.of(10, 0), slot.getStartTime());
//        assertEquals(LocalTime.of(11, 0), slot.getEndTime());
//    }
//
//    @Test
//    void testCreateSlotWithNegativeDuration() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            Slot.create(LocalTime.of(10, 0), -10);
//        });
//        assertEquals("Duração do slot deve ser positiva", exception.getMessage());
//    }
//
//    @Test
//    void testBlockSlot() {
//        slot.block();
//        assertEquals(SlotStatus.BLOCKED, slot.getState());
//    }
//
//    @Test
//    void testBlockReservedSlot() {
//        slot.reserve();
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            slot.block();
//        });
//        assertEquals("Slot não pode ser bloqueado, pois está reservado, cancelado ou reagendado", exception.getMessage());
//    }
//
//    @Test
//    void testCancelSlot() {
//        slot.reserve();
//        slot.cancel();
//        assertEquals(SlotStatus.CANCELLED, slot.getState());
//    }
//
//    @Test
//    void testCancelAvailableSlot() {
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            slot.cancel();
//        });
//        assertEquals("Slot não pode ser cancelado, pois está disponível ou bloqueado", exception.getMessage());
//    }
//
//    @Test
//    void testReserveAvailableSlot() {
//        slot.reserve();
//        assertEquals(SlotStatus.RESERVED, slot.getState());
//    }
//
//    @Test
//    void testReserveBlockedSlot() {
//        slot.block();
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            slot.reserve();
//        });
//        assertEquals("Slot não disponível para reserva", exception.getMessage());
//    }
//
//    @Test
//    void testRescheduleReservedSlot() {
//        slot.reserve();
//        slot.reschedule();
//
//        assertEquals(SlotStatus.RESCHEDULED, slot.getState().getStatus());
//    }
//
//
//    @Test
//    void testRescheduleNonReservedSlot() {
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            slot.reschedule();
//        });
//        assertEquals("Somente slots reservados podem ser reagendados", exception.getMessage());
//    }
//
//    @Test
//    void testReopenCancelledSlot() {
//        slot.reserve();
//        slot.cancel();
//        slot.reopen();
//
//        assertEquals(SlotStatus.AVAILABLE, slot.getState());
//    }
//
//    @Test
//    void testReopenAvailableSlot() {
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            slot.reopen();
//        });
//        assertEquals("Somente slots cancelados ou bloqueados podem ser reabertos", exception.getMessage());
//    }
//}
