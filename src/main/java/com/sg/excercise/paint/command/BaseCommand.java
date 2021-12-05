package com.sg.excercise.paint.command;

import com.sg.excercise.paint.exception.InvalidCommandException;

public interface BaseCommand {

    void execute(String command) throws InvalidCommandException;

    String commandType();
}
