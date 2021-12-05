package com.sg.excercise.paint.model;

import org.springframework.stereotype.Component;

@Component
public class Canvas {

    private CanvasEntity canvasEntity;

    public CanvasEntity getCanvasEntity() {
        return canvasEntity;
    }

    public void setCanvasEntity(CanvasEntity canvasEntity) {
        this.canvasEntity = canvasEntity;
    }
}
