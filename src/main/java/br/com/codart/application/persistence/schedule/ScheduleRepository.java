package br.com.codart.application.persistence.schedule;


import br.com.codart.domain.entity.slot.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {

    @Query("""
    SELECT s FROM ScheduleEntity s
    LEFT JOIN FETCH s.slots sl
    WHERE s.id = :id
    ORDER BY sl.startTime ASC
    """)
    Optional<ScheduleEntity> findScheduleWithSlots(@Param("id") UUID scheduleId);

    @Query("""
    SELECT s FROM ScheduleEntity s
    INNER JOIN FETCH s.slots sl
    WHERE s.id = :id
      AND sl.status = :slotStatus
    ORDER BY sl.startTime ASC
    """)
    Optional<ScheduleEntity> findScheduleWithSlotsByStatus(
            @Param("id") UUID scheduleId,
            @Param("slotStatus") SlotStatus slotStatus
    );

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM slot_sample
            WHERE schedule_id = :scheduleId
              AND slot_status = 'AVAILABLE'
        )
        """, nativeQuery = true)
    boolean existsAvailableSlotByScheduleId(@Param("scheduleId") UUID scheduleId);

    @Query("""
    SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END
    FROM ScheduleEntity s
    WHERE s.scheduleDate = :date
""")
    boolean existsByDate(@Param("date") LocalDate date);
}
