package com.sg.excercise.paint.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandFactory {

    List<BaseCommand> baseCommandList;

    @Autowired
    public CommandFactory(List<BaseCommand> baseCommandList) {
        this.baseCommandList = baseCommandList;
    }

    Map<String, BaseCommand> commandMap;

    @PostConstruct
    public void initCommandMap() {
        commandMap = new HashMap<>();
        baseCommandList.stream().forEach(baseCommand -> commandMap.put(baseCommand.commandType(), baseCommand));
    }

    public BaseCommand getCommand(String commandStr) throws Exception {
        BaseCommand baseCommand = commandMap.get(commandStr);
        if (baseCommand == null) {
            //todo exception handling
            throw new Exception("This command is not supported at the moment.");
        }
        return baseCommand;
    }
}
