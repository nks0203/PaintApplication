package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.LineEntity;
import org.springframework.stereotype.Component;

@Component
public class LineCommandProcessor extends AbstractCommandProcessor {

    @Override
    public void processEntity(BaseEntity baseEntity) {
        LineEntity lineEntity = (LineEntity) baseEntity;
        int x1 = lineEntity.getX1();
        int y1 = lineEntity.getY1();
        int x2 = lineEntity.getX2();
        int y2 = lineEntity.getY2();
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        if (canvasEntity == null) {
            throw new InvalidCommandException("Invalid command. Please create a canvas first. Run -> C <length> <height>");
        }
        for (int row = y1 - 1; row <= y2 - 1 && row < canvasEntity.getWidth(); row++) {
            for (int col = x1 - 1; col <= x2 - 1 && col < canvasEntity.getHeight(); col++) {
                canvas.getCanvasEntity().getCanvasDataArray()[row][col] = 'x';
            }
        }
    }

    @Override
    public BaseEntity validateAndParse(String command) throws InvalidCommandException {
        String[] commandSplit = command.trim().split(" ");
        if (commandSplit.length == 5) {
            int x1 = Integer.parseInt(commandSplit[1]);
            int x2 = Integer.parseInt(commandSplit[2]);
            int y1 = Integer.parseInt(commandSplit[3]);
            int y2 = Integer.parseInt(commandSplit[4]);
            return new LineEntity(x1, x2, y1, y2);
        }
        throw new InvalidCommandException();
    }
}
