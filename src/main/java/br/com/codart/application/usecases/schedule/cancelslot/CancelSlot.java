package br.com.codart.application.usecases.schedule.cancelslot;

import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.repository.DomainScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CancelSlot implements CancelSlotUseCase {

    private final DomainScheduleRepository domainScheduleRepository;

    @Override
    public void execute(final CancelSlotRequestDTO input) {
        log.info("bloqueando um horário na agenda {}", input.getScheduleId());

        Schedule schedule = domainScheduleRepository.findById(input.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("agenda não encontrada para o id fornecido"));

        schedule.cancelSlot(input.getSlotId());

        domainScheduleRepository.save(schedule);
        log.info("horário cancelado na agenda {}", schedule.getId());
    }
}
