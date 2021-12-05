package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.command.processor.validator.BaseCommandValidator;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.Canvas;
import com.sg.excercise.paint.model.CanvasEntity;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCommandProcessor implements BaseCommandProcessor, BaseCommandValidator {

    protected Canvas canvas;

    public void processCommand(String command) {
        BaseEntity baseEntity = validateAndParse(command);
        processEntity(baseEntity);
        renderCommandToCanvas();
    }

    protected void renderCommandToCanvas() {
        StringBuilder builder = new StringBuilder();
        CanvasEntity canvasEntity=canvas.getCanvasEntity();
        String topBottomLine = "";
        for (int i = 0; i < canvasEntity.getHeight()+ 2; i++) {
            topBottomLine += "-";
        }
        builder.append(topBottomLine).append("\n");
        for (int i = 0; i < canvasEntity.getWidth(); i++) {
            builder.append("|");
            for (int j = 0; j < canvasEntity.getHeight(); j++) {
                builder.append(canvasEntity.getCanvasDataArray()[i][j]);
            }
            builder.append("|");
            builder.append("\n");
        }
        builder.append(topBottomLine);
        System.out.println(builder.toString());
    }

    @Autowired
    public void setCanvasEntity(Canvas canvas) {
        this.canvas = canvas;
    }
}
