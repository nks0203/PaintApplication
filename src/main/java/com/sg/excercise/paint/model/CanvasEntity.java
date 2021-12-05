package com.sg.excercise.paint.model;

import java.util.Arrays;

public class CanvasEntity implements BaseEntity {
    private char[][] canvasDataArray;
    private int width;
    private int height;

    public CanvasEntity(int height, int width) {
        this.width = width;
        this.height = height;
        canvasDataArray = new char[this.height][this.height];
        Arrays.stream(canvasDataArray).forEach(chars -> Arrays.fill(chars, ' '));
    }

    public char[][] getCanvasDataArray() {
        return canvasDataArray;
    }

    public void setCanvasDataArray(char[][] canvasDataArray) {
        this.canvasDataArray = canvasDataArray;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
