package com.sg.excercise.paint;

import com.sg.excercise.paint.command.BaseCommand;
import com.sg.excercise.paint.command.CommandFactory;
import com.sg.excercise.paint.command.QuitCanvasEditorCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PaintApplication implements CommandLineRunner {

    @Autowired
    CommandFactory commandFactory;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandStr = scanner.nextLine();
            String[] commandArr = commandStr.split(" ");
            String command = commandArr[0].toUpperCase();
            BaseCommand baseCommand = commandFactory.getCommand(command);
            if (baseCommand.getClass() == QuitCanvasEditorCommand.class) {
                System.out.println("exiting");
                break;
            }
            baseCommand.execute(commandStr);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SpringApplication.run(PaintApplication.class, args);
    }
}
