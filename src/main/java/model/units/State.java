package model.units;

public enum State {
    STATIC("standing"),
    DEFENSIVE("defensive"),
    AGGRESSIVE("offensive");
    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}