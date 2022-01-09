package racxa.sortingalgorithms;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import racxa.sortanimations.AnimateFacade;
import racxa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements Sort{
    private ArrayList <Transition> allT;
    @Override
    public ArrayList<Transition> sort(Rectangle[] arr) {
        allT = new ArrayList<>();
        int n = arr.length;
        int i, j;
        allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR,new ArrayList<>(List.of(0))));
        for (i = 0; i < n; i++) {
            j = i;
            while (j >= 1 && arr[j].getHeight() < arr[j-1].getHeight()) {
                allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR,new ArrayList<>(List.of(j))));
                allT.add(AnimateFacade.swap(arr,j,j-1));
                allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR,new ArrayList<>(List.of(j))));
                j -= 1;
            }
            allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR,new ArrayList<>(List.of(j))));
        }
        for(i = 0; i < n; i++){
            allT.add(AnimateFacade.colorize(arr,Constants.SORTEDARRAYCOLOR,new ArrayList<>(List.of(i))));
        }
        return allT;
    }
}
