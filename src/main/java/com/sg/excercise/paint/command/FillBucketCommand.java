package com.sg.excercise.paint.command;

import com.sg.excercise.paint.command.processor.FillBucketCommandProcessor;
import com.sg.excercise.paint.exception.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FillBucketCommand implements BaseCommand {

    private FillBucketCommandProcessor fillBucketCommandProcessor;

    @Autowired
    public FillBucketCommand(FillBucketCommandProcessor fillBucketCommandProcessor) {
        this.fillBucketCommandProcessor = fillBucketCommandProcessor;
    }

    @Override
    public void execute(String command) throws InvalidCommandException {
        fillBucketCommandProcessor.processCommand(command);
    }

    @Override
    public String commandType() {
        return "B";
    }
}
