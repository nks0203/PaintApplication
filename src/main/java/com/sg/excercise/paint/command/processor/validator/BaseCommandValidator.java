package com.sg.excercise.paint.command.processor.validator;

import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;

public interface BaseCommandValidator {

    BaseEntity validateAndParse(String command) throws InvalidCommandException;
    default boolean isPointOutsideCanvas (int x, int y,int width,int height) {
        return x < 1 || x >= width || y < 1 || y >= height;
    }
}
