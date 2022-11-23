package model;

import java.util.*;

public class JobShop {
    private List<List<Task>> tasks;
    private int nbMachines, nbTasks, nbJobs;
    private Map<Integer, List<Task>> taskByMachine;

    public JobShop(List<List<Task>> tasks, int nMachines, int nbTasks, int nbJobs) {
        this.tasks = tasks;
        this.taskByMachine = new HashMap<>();
        this.nbMachines = nMachines;
        this.nbTasks = nbTasks;
        this.nbJobs = nbJobs;

        for (int i = 0; i < nMachines; i++)
            taskByMachine.put(i, new ArrayList<>());

        int index = 1;
        for (List<Task> tasksBM: tasks){
            for (Task t: tasksBM){
                taskByMachine.get(t.getMachine()).add(t);
                if (t.getId() < 0) t.setId(index++);
            }
        }
    }



    public int getNbTasks() {
        return nbTasks;
    }

    public void setNbTasks(int nbTasks) {
        this.nbTasks = nbTasks;
    }

    public int getNbJobs() {
        return nbJobs;
    }

    public void setNbJobs(int nbJobs) {
        this.nbJobs = nbJobs;
    }

    public int getNbMachines() {
        return nbMachines;
    }

    public void setNbMachines(int nbMachines) {
        this.nbMachines = nbMachines;
    }

    public void setTaskByMachine(Map<Integer, List<Task>> taskByMachine) {
        this.taskByMachine = taskByMachine;
    }

    public List<List<Task>> getTasks() {
        return tasks;
    }

    public void setTasks(List<List<Task>> tasks) {
        this.tasks = tasks;
    }

    public Map<Integer, List<Task>> getTaskByMachine() {
        return taskByMachine;
    }
}

