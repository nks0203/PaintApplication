package com.sg.excercise.paint.command;

import com.sg.excercise.paint.command.processor.CreateCanvasCommandProcessor;
import com.sg.excercise.paint.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCanvasCommand implements BaseCommand {

    private CreateCanvasCommandProcessor createCanvasCommandProcessor;

    @Autowired
    public CreateCanvasCommand(CreateCanvasCommandProcessor createCanvasCommandProcessor) {
        this.createCanvasCommandProcessor = createCanvasCommandProcessor;
    }

    @Override
    public void execute(String command) {
        createCanvasCommandProcessor.processCommand(command);
    }

    @Override
    public String commandType() {
        return "C";
    }
}
