package br.com.codart.application.usecases.schedule.createschedule;

public interface ICreateSchedule {

    CreateScheduleResponseDTO process(CreateScheduleRequestDTO request);
}
