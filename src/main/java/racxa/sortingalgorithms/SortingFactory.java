package racxa.sortingalgorithms;

public class SortingFactory {
    public static Sort createSort(String sortType){
        Sort newSort = null;
        if(sortType.equals("Bubble sort")){
            newSort = new BubbleSort();
        }
        else if(sortType.equals("Insertion sort")){
            newSort = new InsertionSort();
        }
        else if(sortType.equals("Heap sort")){
            newSort = new HeapSort();
        }
        return newSort;
    }

}
