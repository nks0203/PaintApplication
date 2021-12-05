package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CreateCanvasCommandProcessor extends AbstractCommandProcessor {

    @Override
    public void processEntity(BaseEntity baseEntity) {
        CanvasEntity canvasEntity = (CanvasEntity) baseEntity;

        char[][] canvasDataArray = new char[canvasEntity.getWidth()][canvasEntity.getHeight()];
        Arrays.stream(canvasDataArray).forEach(chars -> Arrays.fill(chars, ' '));
        canvasEntity.setCanvasDataArray(canvasDataArray);
        canvas.setCanvasEntity(canvasEntity);
    }

    @Override
    public BaseEntity validateAndParse(String command) throws InvalidCommandException {
        String[] commandSplit = command.split(" ");
        if (commandSplit.length == 3) {
            int height = Integer.parseInt(commandSplit[1]);
            int width = Integer.parseInt(commandSplit[2]);
            if (width > 0 && height > 0) {
                return new CanvasEntity(height, width);
            }
        }
        throw new InvalidCommandException();
    }
}
