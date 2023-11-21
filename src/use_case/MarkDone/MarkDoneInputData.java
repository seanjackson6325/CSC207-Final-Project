package use_case.MarkDone;

public class MarkDoneInputData {
    final private String todoName;

    public MarkDoneInputData(String todoName) {
        this.todoName = todoName;
    }

    public String getTodoName() {
        return todoName;
    }
}
