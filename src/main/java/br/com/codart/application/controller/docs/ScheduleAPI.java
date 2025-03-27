package br.com.codart.application.controller.docs;

import br.com.codart.application.usecases.schedule.addslot.AddSlotRequestDTO;
import br.com.codart.application.usecases.schedule.addslot.AddSlotResponseDTO;
import br.com.codart.application.usecases.schedule.createschedule.CreateScheduleRequestDTO;
import br.com.codart.application.usecases.schedule.createschedule.CreateScheduleResponseDTO;
import br.com.codart.application.usecases.schedule.retrieve.availableslots.GetAvailableSlotsResponseDTO;
import br.com.codart.application.usecases.schedule.retrieve.schedule.GetScheduleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ScheduleAPI {

    @Operation(
            summary = "Busca os detalhes de um agendamento por ID",
            description =
                    "Retorna os detalhes de um agendamento, incluindo data, status e lista de horários disponíveis.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Detalhes do agendamento retornados com sucesso",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<GetScheduleResponseDTO> getSchedule(UUID id);

    @Operation(
            summary = "Lista os horários disponíveis de um agendamento",
            description =
                    "Retorna uma lista de horários disponíveis para o agendamento especificado pelo id.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Lista de horários disponíveis retornada com sucesso",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<List<GetAvailableSlotsResponseDTO>> getAvailableSlots(UUID id);

    @Operation(
            summary = "Abre uma agenda",
            description =
                    "Permite abrir uma nova agenda para uma data específica, com ou sem horários definidos.",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Agenda aberta com sucesso. Retorna o id da agenda criada.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<CreateScheduleResponseDTO> createSchedule(CreateScheduleRequestDTO request);

    @Operation(
            summary = "Adiciona um horário à agenda",
            description =
                    "Adiciona um novo horário a uma agenda existente, identificada pelo id. O horário deve incluir a duração e o horário de início.",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Horário adicionado à agenda com sucesso.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<AddSlotResponseDTO> addSlotToSchedule(UUID id, AddSlotRequestDTO request);

    @Operation(
            summary = "Bloqueia um horário da agenda",
            description = "Bloqueia um horário de uma agenda existente",
            responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Horário bloqueado da agenda com sucesso.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<Void> blockSlotFromSchedule(UUID id, UUID slotId);

    @Operation(
            summary = "Cancela um horário da agenda",
            description = "Cancela um horário de uma agenda existente",
            responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Horário cancelado na agenda com sucesso.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<Void> cancelSlotFromSchedule(UUID id, UUID slotId);

    @Operation(
            summary = "Abre um horário da agenda",
            description = "Abre um horário de uma agenda existente",
            responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Horário aberto na agenda com sucesso.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<Void> openSlotFromSchedule(UUID id, UUID slotId);

    @Operation(
            summary = "Reserva um horário da agenda",
            description = "Reserva um horário de uma agenda existente",
            responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Horário reservado na agenda com sucesso.",
                        content = @Content(mediaType = "application/json"))
            })
    ResponseEntity<Void> reserveSlotFromSchedule(UUID id, UUID slotId);
}
