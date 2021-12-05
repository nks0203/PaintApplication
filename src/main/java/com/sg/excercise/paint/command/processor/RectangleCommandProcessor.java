package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.RectangleEntity;
import org.springframework.stereotype.Component;

@Component
public class RectangleCommandProcessor extends AbstractCommandProcessor {

    @Override
    public void processEntity(BaseEntity baseEntity) {
        RectangleEntity rectangleEntity = (RectangleEntity) baseEntity;
        int x1 = rectangleEntity.getX1();
        int y1 = rectangleEntity.getY1();
        int x2 = rectangleEntity.getX2();
        int y2 = rectangleEntity.getY2();
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        if (canvasEntity == null) {
            throw new InvalidCommandException("Invalid command. Please create a canvas first. Run -> C <length> <height>");
        }
        drawLine(x1, y1, x2, y1, canvasEntity);
        drawLine(x1, y1, x1, y2, canvasEntity);
        drawLine(x2, y1, x2, y2, canvasEntity);
        drawLine(x1, y2, x2, y2, canvasEntity);

    }

    private void drawLine(int x1, int y1, int x2, int y2, CanvasEntity canvasEntity) {
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
            return new RectangleEntity(x1, x2, y1, y2);
        }
        throw new InvalidCommandException();
    }
}