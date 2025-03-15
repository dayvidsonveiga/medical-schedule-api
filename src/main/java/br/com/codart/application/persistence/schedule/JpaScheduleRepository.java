package br.com.codart.application.persistence.schedule;


import br.com.codart.domain.entities.repository.DomainScheduleRepository;
import br.com.codart.domain.entities.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaScheduleRepository implements DomainScheduleRepository {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleEntity entity = scheduleMapper.toEntity(schedule);
        ScheduleEntity savedEntity = scheduleRepository.save(entity);
        return scheduleMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Schedule> findById(UUID scheduleId) {
        return scheduleRepository.findScheduleWithSlots(scheduleId).map(scheduleMapper::toDomain);
    }
}
