package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;

    private long lastId = 0;
    private long lastDeadlineId = 0;

    //init reader and writer
    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    //Method run until "quit"
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals("quit")) {
                break;
            }
            try {
                execute(command);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    //Method use to choose command
    private void execute(String commandLine) throws ParseException {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                showProject();
                break;
            case "add":
                chooseAdd(commandRest[1]);
                break;
            case "check":
                setDone(commandRest[1], true);
                break;
            case "uncheck":
                setDone(commandRest[1], false);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    //Show all project
    private void showProject() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                String deadline = "";
                if(task.getDeadline() != null){
                    deadline = task.getDeadline().getDate().toString();
                }
                out.printf("    [%c] %d: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), deadline);
            }
            out.println();
        }
    }

    //choose the right command add
    private void chooseAdd(String commandLine) throws ParseException {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 3);
            if(projectTask.length == 3){
                addTask(projectTask[0], projectTask[1], projectTask[2]);
            }else{
                addTask(projectTask[0], projectTask[1], null);
            }

        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    private void addTask(String project, String description, String date) throws ParseException {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        Deadline deadline;
        if( date != null){
            Date deadLineDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
            deadline = new Deadline(++lastId, deadLineDate);
        }else{
            deadline = null;
        }

        projectTasks.add(new Task(++lastId, description, false, deadline));
    }

    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    //Show commands
    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description> <task deadline YYYY/MM/DD>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    //Show Error if command doesn't exist
    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
