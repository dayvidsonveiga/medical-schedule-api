package br.com.codart.domain.repository;

import br.com.codart.domain.entity.schedule.Schedule;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface DomainScheduleRepository {

    Schedule save(Schedule schedule);

    Optional<Schedule> findById(UUID scheduleId);

    boolean haveTimeAvailability(UUID scheduleId);

    Optional<Schedule> getScheduleWithSlotStatusEqual(UUID scheduleId, String slotStatus);

    boolean isScheduleDateTaken(LocalDate date);
}
