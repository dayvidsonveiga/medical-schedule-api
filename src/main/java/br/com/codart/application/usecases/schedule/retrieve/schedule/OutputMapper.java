package br.com.codart.application.usecases.schedule.retrieve.schedule;


import br.com.codart.domain.entity.schedule.Schedule;
import br.com.codart.domain.entity.slot.Slot;

import java.util.List;


class OutputMapper {

    private OutputMapper() {}

    public static GetScheduleResponseDTO toOutput(Schedule schedule) {

        List<GetScheduleResponseDTO.GetSlotResponseDTO> slotResponses = schedule.getSlots().stream()
                .map(OutputMapper::toSlotOutput)
                .toList();

        return new GetScheduleResponseDTO(
                schedule.getId(), schedule.getScheduleDate(), schedule.getStatus(), slotResponses);
    }

    public static GetScheduleResponseDTO.GetSlotResponseDTO toSlotOutput(Slot slot) {
        return new GetScheduleResponseDTO.GetSlotResponseDTO(
                slot.getId(),
                slot.getStartTime().toString(),
                slot.getEndTime().toString(),
                slot.getState().getStatus());
    }
}
