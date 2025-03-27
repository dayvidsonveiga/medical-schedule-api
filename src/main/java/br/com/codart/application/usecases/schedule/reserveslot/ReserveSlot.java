package br.com.codart.application.usecases.schedule.reserveslot;

import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.repository.DomainScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReserveSlot implements ReserveSlotUseCase {

    private final DomainScheduleRepository domainScheduleRepository;

    @Override
    public void execute(final ReserveSlotRequestDTO input) {

        log.info("reservando um horário {} na agenda {}", input.getSlotId(), input.getScheduleId());

        Schedule schedule = domainScheduleRepository.findById(input.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("agenda não encontrada para o id fornecido."));

        schedule.reservedSlot(input.getSlotId());

        domainScheduleRepository.save(schedule);
        log.info("horário reservado na agenda {}", schedule.getId());
    }
}
