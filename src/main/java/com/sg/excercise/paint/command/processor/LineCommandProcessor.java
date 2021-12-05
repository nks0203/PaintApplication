package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.config.AppProperties;
import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.LineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class LineCommandProcessor extends AbstractCommandProcessor {


    private AppProperties appProperties;

    @Autowired
    public LineCommandProcessor(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void processEntity(BaseEntity baseEntity) throws InvalidCommandException {
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        if (canvasEntity == null) {
            throw new InvalidCommandException(appProperties.getMessages().getException().getInvalidCommandNoCanvas());
        }

        LineEntity lineEntity = (LineEntity) baseEntity;

        if (isPointOutsideCanvas(lineEntity.getX1(), lineEntity.getY1(), canvasEntity.getWidth(), canvasEntity.getHeight()) ||
                isPointOutsideCanvas(lineEntity.getX1(), lineEntity.getY1(), canvasEntity.getWidth(), canvasEntity.getHeight())) {
            throw new InvalidCommandException(MessageFormat.format(appProperties.getMessages().getException().getInvalidCommandPointOutsideCanvas(),canvasEntity.getWidth(),canvasEntity.getHeight()));
        }
        int x1 = lineEntity.getX1() - 1;
        int y1 = lineEntity.getY1() - 1;
        int x2 = lineEntity.getX2() - 1;
        int y2 = lineEntity.getY2() - 1;

        for (int i = x1; i <= x2 && i < canvasEntity.getHeight(); i++) {
            for (int j = y1; j <= y2 && j < canvasEntity.getWidth(); j++) {
                canvas.getCanvasEntity().getCanvasDataArray()[i][j] = appProperties.getCanvas().getLineChar();
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
