package com.sg.excercise.paint.command.processor;

import com.sg.excercise.paint.model.BaseEntity;

public interface BaseCommandProcessor {

    void processEntity(BaseEntity baseEntity);
}
