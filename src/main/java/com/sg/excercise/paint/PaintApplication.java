package com.sg.excercise.paint;

import com.sg.excercise.paint.command.BaseCommand;
import com.sg.excercise.paint.command.CommandFactory;
import com.sg.excercise.paint.command.QuitCanvasEditorCommand;
import com.sg.excercise.paint.exception.CommandNotSupportedException;
import com.sg.excercise.paint.exception.InvalidCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PaintApplication implements CommandLineRunner {

    @Autowired
    CommandFactory commandFactory;

    public static void main(String[] args) {
        SpringApplication.run(PaintApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandStr = scanner.nextLine();
            String[] commandArr = commandStr.split(" ");
            String command = commandArr[0].toUpperCase();
            try {
                BaseCommand baseCommand = commandFactory.getCommand(command.toUpperCase());
                baseCommand.execute(commandStr);
                if (baseCommand.getClass() == QuitCanvasEditorCommand.class) {
                    System.out.println("Terminating...! ");
                    break;
                }
            } catch (InvalidCommandException ice) {
                System.out.println("INVALID COMMAND:" + ice.getMessage());
            } catch (CommandNotSupportedException cse) {
                System.out.println("COMMAND NOT FOUND:" + cse.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println("INVALID ARGS:" + iae.getMessage());
            }


        }
        System.exit(0);
    }
}
