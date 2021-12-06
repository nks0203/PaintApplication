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
        System.out.println("\u001B[31m" + "Welcome to Paint!\nSupported commands are:\n1. C <width> <height> ; 0<width<=1000 , 0<height<=1000.\n" +
                "2. L x1 y1 x2 y2 :\t Create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character.\n" +
                "3. R x1 y1 x2 y2 :\t Create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.\n" +
                "4. B x y c :\tFills the entire area connected to (x,y) with \"colour\" c. The behaviour of this is the same as that of the \"bucket fill\" tool in paint programs.\n" +
                "5. Q :\tQuits the program.\n\nTo start , create a canvas using command 1." + "\u001B[0m");
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
