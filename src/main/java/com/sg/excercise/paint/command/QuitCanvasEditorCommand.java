package com.sg.excercise.paint.command;

import com.sg.excercise.paint.model.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class QuitCanvasEditorCommand implements BaseCommand {

    @Override
    public void execute(String command) {

    }

    @Override
    public String commandType() {
        return "Q";
    }
}
