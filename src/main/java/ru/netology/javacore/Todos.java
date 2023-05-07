package ru.netology.javacore;

import java.util.*;

public class Todos {
    private static final int MAXIMUM_TASKS = 7;
    private final TreeSet<String> toDoList = new TreeSet<>();
    private final Deque<Pair> operationStack = new LinkedList<>();

    public void addTask(String task) {
        if (addTaskWithoutWritingToTheStack(task)) {
            operationStack.push(new Pair(Operation.ADD, task));
        }
    }

    private boolean addTaskWithoutWritingToTheStack(String task) {
        if (toDoList.size() < MAXIMUM_TASKS) {
            toDoList.add(task);
            return true;
        }
        return false;
    }

    public void removeTask(String task) {
        if (removeTaskWithoutWritingToTheStack(task)) {
            operationStack.push(new Pair(Operation.REMOVE, task));
        }
    }

    private boolean removeTaskWithoutWritingToTheStack(String task) {
        if (toDoList.contains(task)) {
            toDoList.remove(task);
            return true;
        }
        return false;
    }

    public void restoreTask() {
        System.out.println(" ");
        if (!operationStack.isEmpty()) {
            var pair = operationStack.pop();
            switch (pair.operation) {
                case REMOVE: addTaskWithoutWritingToTheStack(pair.task);
                    break;
                case ADD: removeTaskWithoutWritingToTheStack(pair.task);
                    break;
            }
        }
    }

    public String getAllTasks() {
        var stringBuilder = new StringBuilder();
        for (String s : toDoList) {
            stringBuilder.append(s);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private class Pair {
        Operation operation;
        String task;

        public Pair(Operation operation, String task) {
            this.operation = operation;
            this.task = task;
        }
    }
}
