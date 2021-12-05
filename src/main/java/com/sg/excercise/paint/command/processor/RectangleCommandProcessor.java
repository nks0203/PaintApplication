package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.config.AppProperties;
import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.RectangleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class RectangleCommandProcessor extends AbstractCommandProcessor {

    private AppProperties appProperties;

    @Autowired
    public RectangleCommandProcessor(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void processEntity(BaseEntity baseEntity) throws InvalidCommandException {
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        if (canvasEntity == null) {
            throw new InvalidCommandException(appProperties.getMessages().getException().getInvalidCommandNoCanvas());
        }

        RectangleEntity rectangleEntity = (RectangleEntity) baseEntity;
        if (isPointOutsideCanvas(rectangleEntity.getX1(), rectangleEntity.getY1(), canvasEntity.getWidth(), canvasEntity.getHeight()) ||
                isPointOutsideCanvas(rectangleEntity.getX1(), rectangleEntity.getY1(), canvasEntity.getWidth(), canvasEntity.getHeight())) {
            throw new InvalidCommandException(MessageFormat.format(appProperties.getMessages().getException().getInvalidCommandPointOutsideCanvas(),canvasEntity.getWidth(),canvasEntity.getHeight()));
        }

        int x1 = rectangleEntity.getX1() - 1;
        int y1 = rectangleEntity.getY1() - 1;
        int x2 = rectangleEntity.getX2() - 1;
        int y2 = rectangleEntity.getY2() - 1;

        drawLine(x1, y1, x2, y1, canvasEntity);
        drawLine(x1, y1, x1, y2, canvasEntity);
        drawLine(x2, y1, x2, y2, canvasEntity);
        drawLine(x1, y2, x2, y2, canvasEntity);
    }

    private void drawLine(int x1, int y1, int x2, int y2, CanvasEntity canvasEntity) {
        for (int row = y1; row <= y2 && row < canvasEntity.getWidth(); row++) {
            for (int col = x1; col <= x2 && col < canvasEntity.getHeight(); col++) {
                canvas.getCanvasEntity().getCanvasDataArray()[row][col] = appProperties.getCanvas().getRectangleChar();
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
