package br.com.codart.application.persistence.schedule;


import br.com.codart.domain.repository.DomainScheduleRepository;
import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.entity.slot.SlotStatus;
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

    @Override
    public boolean haveTimeAvailability(UUID scheduleId) {
        return scheduleRepository.existsAvailableSlotByScheduleId(scheduleId);
    }

    @Override
    public Optional<Schedule> getScheduleWithSlotStatusEqual(UUID id, String slotStatus) {
        return scheduleRepository
                .findScheduleWithSlotsByStatus(id, SlotStatus.valueOf(slotStatus))
                .map(scheduleMapper::toDomain);
    }

    @Override
    public boolean isScheduleDateTaken(LocalDate date) {
        return scheduleRepository.existsByDate(date);
    }
}
