package org.answer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Bean that represents a flight segment.
 * Сегмент — это атомарная перевозка, которую для простоты будем характеризовать всего двумя атрибутами:
 * дата/время вылета и дата/время прилёта
 */

public class Segment {
    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    public Segment(final LocalDateTime dep, final LocalDateTime arr) {
        this.departureDate = Objects.requireNonNull(dep);
        this.arrivalDate = Objects.requireNonNull(arr);
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}
