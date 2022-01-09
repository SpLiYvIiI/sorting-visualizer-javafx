package racxa.sortingalgorithms;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import racxa.sortanimations.AnimateFacade;
import racxa.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class BubbleSort implements Sort{
    private ArrayList<Transition> allT;
    public ArrayList<Transition> sort(Rectangle[] arr) {
        allT = new ArrayList<>();
        int n = arr.length;
        boolean s = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-i-1; j++){
                allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR, new ArrayList<>(List.of(j,j+1))));
                if(arr[j].getHeight() > arr[j+1].getHeight()){
                    s = false;
                    allT.add(AnimateFacade.swap(arr,j,j+1));
                    allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR, new ArrayList<>(List.of(j))));
                }
                else
                    allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR, new ArrayList<>(List.of(j,j+1))));
            }
            if(s) {
                for (int z = 0; z < n-i-1; z++) {
                    allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR, new ArrayList<>(List.of(z))));
                }
                break;
            }
            s = true;
            allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR,new ArrayList<>(List.of(n-i-1))));
        }
        for(int i = 0; i < n; i++){
            allT.add(AnimateFacade.colorize(arr,Constants.SORTEDARRAYCOLOR,new ArrayList<>(List.of(i))));
        }
        return allT;
    }

}
