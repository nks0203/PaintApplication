package com.sg.excercise.paint.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private CanvasProperties canvas;
    private Message messages;

    public Message getMessages() {
        return messages;
    }

    public void setMessages(Message messages) {
        this.messages = messages;
    }

    public CanvasProperties getCanvas() {
        return canvas;
    }

    public void setCanvas(CanvasProperties canvas) {
        this.canvas = canvas;
    }

    public static class Message {
        private Exception exception;
        private String quitMessage;

        public String getQuitMessage() {
            return quitMessage;
        }

        public void setQuitMessage(String quitMessage) {
            this.quitMessage = quitMessage;
        }

        public Exception getException() {
            return exception;
        }

        public void setException(Exception exception) {
            this.exception = exception;
        }
    }

    public static class Exception {
        private String invalidCommandNoCanvas;
        private String invalidCommandWrongArgs;
        private String invalidCommandDiagonalLine;
        private String commandNotSupported;

        public String getCommandNotSupported() {
            return commandNotSupported;
        }

        public void setCommandNotSupported(String commandNotSupported) {
            this.commandNotSupported = commandNotSupported;
        }

        public String getInvalidCommandWrongArgs() {
            return invalidCommandWrongArgs;
        }

        public void setInvalidCommandWrongArgs(String invalidCommandWrongArgs) {
            this.invalidCommandWrongArgs = invalidCommandWrongArgs;
        }

        public String getInvalidCommandDiagonalLine() {
            return invalidCommandDiagonalLine;
        }

        public void setInvalidCommandDiagonalLine(String invalidCommandDiagonalLine) {
            this.invalidCommandDiagonalLine = invalidCommandDiagonalLine;
        }

        public String getInvalidCommandNoCanvas() {
            return invalidCommandNoCanvas;
        }

        public void setInvalidCommandNoCanvas(String invalidCommandNoCanvas) {
            this.invalidCommandNoCanvas = invalidCommandNoCanvas;
        }
    }

    public static class CanvasProperties {
        private char boundryHorizontalChar;
        private char boundryVerticalChar;
        private char defaultFillChar;
        private char rectangleChar;
        private char lineChar;

        public char getDefaultFillChar() {
            return defaultFillChar;
        }

        public void setDefaultFillChar(char defaultFillChar) {
            this.defaultFillChar = defaultFillChar;
        }

        public char getBoundryHorizontalChar() {
            return boundryHorizontalChar;
        }

        public void setBoundryHorizontalChar(char boundryHorizontalChar) {
            this.boundryHorizontalChar = boundryHorizontalChar;
        }

        public char getBoundryVerticalChar() {
            return boundryVerticalChar;
        }

        public void setBoundryVerticalChar(char boundryVerticalChar) {
            this.boundryVerticalChar = boundryVerticalChar;
        }

        public char getRectangleChar() {
            return rectangleChar;
        }

        public void setRectangleChar(char rectangleChar) {
            this.rectangleChar = rectangleChar;
        }

        public char getLineChar() {
            return lineChar;
        }

        public void setLineChar(char lineChar) {
            this.lineChar = lineChar;
        }
    }


}
