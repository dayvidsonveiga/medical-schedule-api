package br.com.codart.application.usecases.schedule.blockslot;

import br.com.codart.domain.repository.DomainScheduleRepository;
import br.com.codart.domain.entity.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlockSlot implements BlockSlotUseCase {

    private final DomainScheduleRepository domainScheduleRepository;

    @Override
    public void execute(final BlockSlotRequestDTO input) {
        log.info("bloqueando um horário na agenda {}", input.getScheduleId());

        Schedule schedule = domainScheduleRepository.findById(input.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("agenda não encontrada para o id fornecido."));

        schedule.blockSlot(input.getSlotId());

        domainScheduleRepository.save(schedule);
        log.info("horário bloqueado na agenda {}", schedule.getId());
    }
}
