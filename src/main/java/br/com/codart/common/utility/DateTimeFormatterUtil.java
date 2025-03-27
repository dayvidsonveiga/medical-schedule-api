package br.com.codart.common.utility;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateTimeFormatterUtil {

    private static final DateTimeFormatter DAY_FORMATTER =
            DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", Locale.getDefault());
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Formata uma data para o padrão "segunda-feira, 7 de dezembro de 2024".
     *
     * @param date a data a ser formatada.
     * @return a data formatada.
     */
    public String formatDay(LocalDate date) {
        return DAY_FORMATTER.format(date);
    }

    /**
     * Formata um intervalo de tempo para "08:00 - 08:30".
     *
     * @param start o horário de início.
     * @param end o horário de término.
     * @return o intervalo formatado.
     */
    public String formatTime(LocalTime start, LocalTime end) {
        return TIME_FORMATTER.format(start).concat(" - ").concat(TIME_FORMATTER.format(end));
    }

    /**
     * Calcula a duração entre dois horários em minutos e formata para "30 minutos".
     *
     * @param start o horário de início.
     * @param end o horário de término.
     * @return a duração formatada.
     */
    public String calculateDuration(LocalTime start, LocalTime end) {
        long minutes =
                Duration.between(start.atDate(LocalDate.now()), end.atDate(LocalDate.now())).toMinutes();
        return minutes + " minutos";
    }
}
