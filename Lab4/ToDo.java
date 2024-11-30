public class ToDo {

    private final int userId;
    private final int id;
    private final String title;
    private final boolean completed;

    public ToDo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        String booleanString = (completed ? "true" : "false");
        return String.format("""
                userId: %d
                id: %d
                title: %s
                completed: %s""", userId, id, title, booleanString);
    }
}
