package br.com.codart.application.persistence.schedule;


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
}
