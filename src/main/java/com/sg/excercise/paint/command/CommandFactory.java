package com.sg.excercise.paint.command;

import com.sg.excercise.paint.config.AppProperties;
import com.sg.excercise.paint.exception.CommandNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandFactory {

    Map<String, BaseCommand> commandMap;
    private List<BaseCommand> baseCommandList;
    private AppProperties appProperties;

    @Autowired
    public CommandFactory(List<BaseCommand> baseCommandList, AppProperties appProperties) {
        this.baseCommandList = baseCommandList;
        this.appProperties = appProperties;
    }

    @PostConstruct
    public void initCommandMap() {
        commandMap = new HashMap<>();
        baseCommandList.stream().forEach(baseCommand -> commandMap.put(baseCommand.commandType(), baseCommand));
    }

    public BaseCommand getCommand(String commandStr) throws CommandNotSupportedException {
        BaseCommand baseCommand = commandMap.get(commandStr);
        if (baseCommand == null) {
            throw new CommandNotSupportedException(appProperties.getMessages().getException().getCommandNotSupported());
        }
        return baseCommand;
    }
}
