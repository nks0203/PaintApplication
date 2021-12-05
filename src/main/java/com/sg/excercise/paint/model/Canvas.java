package com.sg.excercise.paint.model;

import com.sg.excercise.paint.model.CanvasEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
