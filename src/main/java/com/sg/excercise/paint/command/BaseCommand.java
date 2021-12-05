package com.sg.excercise.paint.command;

public interface BaseCommand {

    void execute(String command);

    String commandType();
}
