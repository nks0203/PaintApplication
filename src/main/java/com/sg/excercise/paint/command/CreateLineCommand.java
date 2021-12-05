package com.sg.excercise.paint.command;

import com.sg.excercise.paint.command.processor.LineCommandProcessor;
import com.sg.excercise.paint.exception.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateLineCommand implements BaseCommand {

    private LineCommandProcessor lineCommandProcessor;

    @Autowired
    public CreateLineCommand(LineCommandProcessor lineCommandProcessor) {
        this.lineCommandProcessor = lineCommandProcessor;
    }

    @Override
    public void execute(String command) throws InvalidCommandException {
        lineCommandProcessor.processCommand(command);
    }

    @Override
    public String commandType() {
        return "L";
    }
}
