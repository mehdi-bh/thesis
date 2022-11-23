package model;

public class Task {
    private int id,machine, dur;
    private String title;

    public Task(int machine, int dur) {
        this(-1,machine,dur, "");
    }

    public Task(int id, int machine, int dur) {
        this(id,machine,dur, "");
    }

    public Task(int id, int machine, int dur, String title) {
        this.id = id;
        this.machine = machine;
        this.dur = dur;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }


}
