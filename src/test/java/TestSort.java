import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racxa.sortingalgorithms.Sort;
import racxa.sortingalgorithms.SortingFactory;
import racxa.utils.Constants;
import racxa.utils.Helper;

import java.util.Arrays;
import java.util.Random;


public class TestSort {
    private Rectangle[] arr;
    private static Random r = new Random();
    private Sort toTest;
    private Rectangle[] sortedArr;

    @BeforeEach
    void init() {
        Constants.ARRAYSIZE = 60;
        Constants.OFFSET = Constants.PANEWIDTH / Constants.ARRAYSIZE;
        Constants.SORTINGSPEED = 20/(1+r.nextInt(4));
        arr = Helper.generateArr();
        sortedArr = arr.clone();
        Arrays.sort(sortedArr,(a,b) -> (int) (a.getHeight() - b.getHeight()));
    }
    @Test
     void bubbleSort(){
        toTest = SortingFactory.createSort("Bubble sort");
        toTest.sort(arr);
        for(int i = 0; i < arr.length; i++){
            Assertions.assertEquals(sortedArr[i].getHeight(),arr[i].getHeight());
        }
    }
    @Test
    void insertionSort(){
        toTest = SortingFactory.createSort("Insertion sort");
        toTest.sort(arr);
        for(int i = 0; i < arr.length; i++){
            Assertions.assertEquals(sortedArr[i].getHeight(),arr[i].getHeight());
        }
    }
    @Test
    void heapSort(){
        toTest = SortingFactory.createSort("Heap sort");
        toTest.sort(arr);
        for(int i = 0; i < arr.length; i++){
            Assertions.assertEquals(sortedArr[i].getHeight(),arr[i].getHeight());
        }
    }

}
