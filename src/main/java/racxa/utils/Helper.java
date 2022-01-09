package racxa.utils;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class Helper {

    public static final Rectangle[] generateArr() {
        Rectangle elements[] = new Rectangle[Constants.ARRAYSIZE];
        Random r = new Random();
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Rectangle();
            elements[i].setX(i * (Constants.PANEWIDTH / Constants.ARRAYSIZE));
            elements[i].setFill(Color.BLACK);
            elements[i].setWidth(Constants.PANEWIDTH / Constants.ARRAYSIZE - Constants.SPACING);
            elements[i].setHeight(1 + r.nextInt(Constants.PANEHEIGHT));
        }
        return elements;
    }
}
