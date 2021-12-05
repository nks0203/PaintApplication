package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.config.AppProperties;
import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.FillBucketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FillBucketCommandProcessor extends AbstractCommandProcessor {

    private AppProperties appProperties;

    @Autowired
    public FillBucketCommandProcessor(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void processEntity(BaseEntity baseEntity) throws InvalidCommandException {
        FillBucketEntity fillBucketEntity = (FillBucketEntity) baseEntity;
        CanvasEntity canvasEntity = canvas.getCanvasEntity();
        if (canvasEntity == null) {
            throw new InvalidCommandException(appProperties.getMessages().getException().getInvalidCommandNoCanvas());
        }
        int x1 = fillBucketEntity.getX1();
        int y1 = fillBucketEntity.getY1();
        char originalChar = canvasEntity.getCanvasDataArray()[y1 - 1][x1 - 1];
        recursiveFillBucketWithChar(x1 - 1, y1 - 1, originalChar, fillBucketEntity.getFillByChar(), canvasEntity);

    }

    private void recursiveFillBucketWithChar(int x1, int y1, char originalChar, char fillByChar, CanvasEntity canvasEntity) {
        char[][] canvasDataArray = canvasEntity.getCanvasDataArray();

        if ((x1 >= 0 && x1 < canvasEntity.getWidth()) && (y1 >= 0 && y1 < canvasEntity.getHeight())) {
            if (canvasDataArray[x1][y1] == originalChar) {

                canvasEntity.getCanvasDataArray()[x1][y1] = fillByChar;
                recursiveFillBucketWithChar(x1 + 1, y1, originalChar, fillByChar, canvasEntity);
                recursiveFillBucketWithChar(x1 - 1, y1, originalChar, fillByChar, canvasEntity);
                recursiveFillBucketWithChar(x1, y1 + 1, originalChar, fillByChar, canvasEntity);
                recursiveFillBucketWithChar(x1, y1 - 1, originalChar, fillByChar, canvasEntity);
            }
        }
    }

    @Override
    public BaseEntity validateAndParse(String command) throws InvalidCommandException {
        String[] commandSplit = command.trim().split(" ");
        if (commandSplit.length == 4) {
            int x1 = Integer.parseInt(commandSplit[1]);
            int y1 = Integer.parseInt(commandSplit[2]);
            if (commandSplit[3].toCharArray().length == 1) {
                char fillByChar = commandSplit[3].charAt(0);
                return new FillBucketEntity(x1, y1, fillByChar);
            }
        }
        throw new InvalidCommandException();
    }
}
