package com.sg.excercise.paint.command.processor.validator;

import com.sg.excercise.paint.exception.InvalidCommandException;
import com.sg.excercise.paint.model.BaseEntity;

public interface BaseCommandValidator {

    BaseEntity validateAndParse(String command) throws InvalidCommandException;
}
