package racxa.sortingalgorithms;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import racxa.sortanimations.AnimateFacade;
import racxa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class HeapSort implements Sort{
    ArrayList<Transition> allT;
    @Override
    public ArrayList<Transition> sort(Rectangle[] arr) {
        allT = new ArrayList<>();
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR,new ArrayList<>(List.of(0,i))));
            allT.add(AnimateFacade.swap(arr,0, i));
            allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR,new ArrayList<>(List.of(0,i))));
            allT.add(AnimateFacade.colorize(arr, Constants.SORTEDELEMENTCOLOR,new ArrayList<>(List.of(i))));
            heapify(arr, i, 0);
        }
        for(int i = 0; i < n; i++){
            allT.add(AnimateFacade.colorize(arr,Constants.SORTEDARRAYCOLOR,new ArrayList<>(List.of(i))));
        }
        return allT;
    }
    void heapify(Rectangle arr[], int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n){
            allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR,new ArrayList<>(List.of(l,largest))));
            allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR,new ArrayList<>(List.of(l,largest))));
            if(arr[l].getHeight() > arr[largest].getHeight())
                largest = l;
        }
        if (r < n){
        allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR,new ArrayList<>(List.of(r,largest))));
        allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR,new ArrayList<>(List.of(r,largest))));
        if(arr[r].getHeight() > arr[largest].getHeight())
            largest = r;
        }
        if (largest != i) {
            allT.add(AnimateFacade.colorize(arr, Constants.ACTIVEELEMENTCOLOR,new ArrayList<>(List.of(i,largest))));
            allT.add(AnimateFacade.swap(arr,i, largest));
            allT.add(AnimateFacade.colorize(arr, Constants.DEFAULTCOLOR,new ArrayList<>(List.of(i,largest))));
            heapify(arr, n, largest);
        }
    }


}
