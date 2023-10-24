package entity;

import java.time.LocalDateTime;

public interface TodoInterface {

    String getName();

    String getDescription();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    User getRequester();

    User getRequestedTo();

    Boolean getStatus();

}
