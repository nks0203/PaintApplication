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
        fillBucketWithChar(x1 - 1, y1 - 1, originalChar, fillBucketEntity.getFillByChar(), canvasEntity);

    }


    private void fillBucketWithChar(int x1, int y1, char originalChar, char fillByChar, CanvasEntity canvasEntity) {
        Stack<Point> recursiveStack = new Stack<>();
        char[][] canvasDataArray = canvasEntity.getCanvasDataArray();
        recursiveStack.add(new Point(x1, y1));
        while (!recursiveStack.isEmpty()) {
            Point point = recursiveStack.pop();
            if (canvasDataArray[point.getX1()][point.getY1()] == originalChar) {
                canvasDataArray[point.getX1()][point.getY1()] = fillByChar;
            }
            if (point.getX1() - 1 >= 0 && canvasDataArray[point.getX1() - 1][point.getY1()] == originalChar) {
                recursiveStack.add(new Point(point.getX1() - 1, point.getY1()));
            }
            if (point.getX1() + 1 < canvasEntity.getHeight() && canvasDataArray[point.getX1() + 1][point.getY1()] == originalChar) {
                recursiveStack.add(new Point(point.getX1() + 1, point.getY1()));
            }
            if (point.getY1() - 1 >= 0 && canvasDataArray[point.getX1()][point.getY1() - 1] == originalChar) {
                recursiveStack.add(new Point(point.getX1(), point.getY1() - 1));
            }
            if (point.getY1() + 1 < canvasEntity.getWidth() && canvasDataArray[point.getX1()][point.getY1() + 1] == originalChar) {
                recursiveStack.add(new Point(point.getX1(), point.getY1() + 1));
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
