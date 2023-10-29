package entity;

import java.time.LocalDateTime;

public class Todo implements TodoInterface {

    private final String name;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String requester;
    private final String requestedTo;
    private final Boolean status;

    /**
     * Constructor for To-do()
     *
     * @param name   Name of to-do
     * @param description   Description of the to-do
     * @param startTime   The start date/time of the to-do
     * @param endTime   The due date/time of the to-do
     * @param requester   User that created the to-do
     * @param requestedTo   User that the to-do is requested to
     * @param status   Status of completion, True = finished
     *
     */
    public Todo(String name, String description, LocalDateTime startTime, LocalDateTime endTime, String requester,
         String requestedTo, Boolean status) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requester = requester;
        this.requestedTo = requestedTo;
        this.status = status;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String getRequester() {
        return this.requester;
    }

    @Override
    public String getRequestedTo() {
        return this.requestedTo;
    }

    @Override
    public Boolean getStatus() {
        return this.status;
    }
}
