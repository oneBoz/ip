package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public abstract String toRawString();

    @Override
    public boolean equals(Object task) {
        if (task == this) {
            return true;
        } else if (task instanceof Task) {
            return this.toRawString().equals(((Task) task).toRawString());
        }
        return false;
    }
}

