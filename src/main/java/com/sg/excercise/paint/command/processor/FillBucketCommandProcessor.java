package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.config.AppProperties;
import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;
import com.sg.excercise.paint.model.CanvasEntity;
import com.sg.excercise.paint.model.FillBucketEntity;
import com.sg.excercise.paint.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Stack;

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
        if (isPointOutsideCanvas(x1, y1, canvasEntity.getWidth(), canvasEntity.getHeight())) {
            throw new InvalidCommandException(MessageFormat.format(appProperties.getMessages().getException().getInvalidCommandPointOutsideCanvas(), canvasEntity.getWidth(), canvasEntity.getHeight()));
        }
        char originalChar = canvasEntity.getCanvasDataArray()[y1 - 1][x1 - 1];
        Stack<Point> recursiveStack = new Stack<>();
        recursiveStack.add(new Point(x1 - 1, y1 - 1));
        fillBucketWithChar(recursiveStack, originalChar, fillBucketEntity.getFillByChar(), canvasEntity);

    }

    private void fillBucketWithChar(Stack<Point> recursiveStack, char originalChar, char fillByChar, CanvasEntity canvasEntity) {
        char[][] canvasDataArray = canvasEntity.getCanvasDataArray();
        while (!recursiveStack.isEmpty()) {
            Point point = recursiveStack.pop();
            int x = point.getX1();
            int y = point.getY1();
            if (canvasDataArray[x][y] == originalChar) {
                canvasDataArray[x][y] = fillByChar;
            }
            if (x + 1 < canvasEntity.getHeight() && canvasDataArray[x + 1][y] == originalChar) {
                recursiveStack.add(new Point(x + 1, y));
            }
            if (x - 1 >= 0 && canvasDataArray[x - 1][y] == originalChar) {
                recursiveStack.add(new Point(x - 1, y));
            }
            if (y + 1 < canvasEntity.getWidth() && canvasDataArray[x][y + 1] == originalChar) {
                recursiveStack.add(new Point(x, y + 1));
            }
            if (y - 1 >= 0 && canvasDataArray[x][y - 1] == originalChar) {
                recursiveStack.add(new Point(x, y - 1));
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
