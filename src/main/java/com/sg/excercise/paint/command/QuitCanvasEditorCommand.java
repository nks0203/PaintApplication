package com.sg.excercise.paint.command;

import com.sg.excercise.paint.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuitCanvasEditorCommand implements BaseCommand {
    private AppProperties appProperties;

    @Autowired
    public QuitCanvasEditorCommand(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public void execute(String command) {
        System.out.println(appProperties.getMessages().getQuitMessage());
    }

    @Override
    public String commandType() {
        return "Q";
    }
}
