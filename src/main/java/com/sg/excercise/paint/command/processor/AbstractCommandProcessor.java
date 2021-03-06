package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.command.processor.validator.BaseCommandValidator;
import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.Canvas;
import com.sg.excercise.paint.model.CanvasEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommandProcessor implements BaseCommandProcessor, BaseCommandValidator {

    protected Canvas canvas;

    public void processCommand(String command) throws InvalidCommandException {
        BaseEntity baseEntity = validateAndParse(command);
        processEntity(baseEntity);
        drawOnConsole();
    }

    protected void drawOnConsole() {
        StringBuilder builder = new StringBuilder();
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        String topBottomLine = "";
        for (int i = 0; i < canvasEntity.getWidth() + 2; i++) {
            topBottomLine += "-";
        }
        builder.append(topBottomLine).append("\n");
        for (int i = 0; i < canvasEntity.getHeight(); i++) {
            builder.append("|");
            for (int j = 0; j < canvasEntity.getWidth(); j++) {
                builder.append(canvasEntity.getCanvasDataArray()[i][j]);
            }
            builder.append("|");
            builder.append("\n");
        }
        builder.append(topBottomLine);
        System.out.println(builder.toString());
    }

    @Autowired
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
