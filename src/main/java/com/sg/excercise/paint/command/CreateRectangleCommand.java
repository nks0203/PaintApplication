package com.sg.excercise.paint.command;

import com.sg.excercise.paint.command.processor.RectangleCommandProcessor;
import com.sg.excercise.paint.exception.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRectangleCommand implements BaseCommand {

    private RectangleCommandProcessor rectangleCommandProcessor;

    @Autowired
    public CreateRectangleCommand(RectangleCommandProcessor rectangleCommandProcessor) {
        this.rectangleCommandProcessor = rectangleCommandProcessor;
    }

    @Override
    public void execute(String command) throws InvalidCommandException {
        rectangleCommandProcessor.processCommand(command);
    }

    @Override
    public String commandType() {
        return "R";
    }
}
