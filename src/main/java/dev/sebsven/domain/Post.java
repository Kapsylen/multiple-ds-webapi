package dev.sebsven.domain;

import java.time.LocalDateTime;

public record Post(
        String id,
        String title,
        String text,
        LocalDateTime date,
        int time_to_read,
        String tags
) {
}
