package br.com.codart.domain.entities.repository;

import br.com.codart.domain.entities.schedule.Schedule;

import java.util.Optional;
import java.util.UUID;

public interface DomainScheduleRepository {

    Schedule save(Schedule schedule);

    Optional<Schedule> findById(UUID scheduleId);
}
