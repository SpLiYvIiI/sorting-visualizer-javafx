package racxa.sortanimations;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static racxa.utils.Constants.OFFSET;

public class AnimateFacade {
    private static ParallelTransition p;
    public static final ParallelTransition swap(Rectangle arr[], int i, int j) {
        p = new ParallelTransition();
        TranslateTransition first = Animate.swapAnimation(arr[i],OFFSET*(j-i));
        TranslateTransition second = Animate.swapAnimation(arr[j],-OFFSET*(j-i));
        p.getChildren().addAll(first,second);
        Rectangle tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return p;
    }
    public static final ParallelTransition colorize(Rectangle arr[], Color c, ArrayList<Integer> a){
        p = new ParallelTransition();
        for(Integer i : a){
            p.getChildren().add(Animate.colorAnimation(arr[i],c));
        }
        return p;
    }
}
