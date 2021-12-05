package com.sg.excercise.paint.model;

import com.sg.excercise.paint.util.PaintUtils;

public class RectangleEntity implements BaseEntity {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public RectangleEntity(int x1, int y1, int x2, int y2) {

        PaintUtils.areAllPointsPositive(x1, x2, y1, y2);
        if ((x1 == x2 && y2 > y1) || (y1 == y2 && x2 > x1)) {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
