package racxa.sortanimations;

import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import racxa.utils.Constants;

public class Animate {
    public static final TranslateTransition swapAnimation(Rectangle i, double offSett) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(i);
        translate.setByX(offSett);
        translate.setDuration(Duration.millis(1));
        return translate;
    }
    public static final FillTransition colorAnimation(Rectangle i, Color c){
        FillTransition f = new FillTransition();
        f.setShape(i);
        f.setToValue(c);
        f.setDuration(Duration.millis(Constants.SORTINGSPEED));
        return f;
    }
}
