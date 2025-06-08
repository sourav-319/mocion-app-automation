package org.mocion.app.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GestureCoordinates {
    private int startX1;
    private int startY1;
    private int startX2;
    private int startY2;
    private int endX1;
    private int endY1;
    private int endX2;
    private int endY2;
}
