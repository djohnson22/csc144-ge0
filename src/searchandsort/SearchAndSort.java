package searchandsort;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class contains methods for a variety of searches and sorts.
 * Can create lists of random values, and complete sorts and searches. 
 * Sorts include: Selection sort, Insertion sort, and Merge sort
 * Searches include: Sequential search, Binary search
 * @author djohnson22
 */

public class SearchAndSort {
    private static final Random rng = new Random();
    private static final int SIZE_THRESHOLD = 20;
    
    /**
     * This method creates a list of random values for later use.
     * @param size the size (legnth) of the list to be created
     * @return the newly created list
     */
    
    public static List<Integer> makeList(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int n = 10 + rng.nextInt(90);
            result.add(n);
        }//for
        return result;
    }//makeList
    
    /**
     * This method is used to print the lists used in the searches and sorts.
     * @param values the list to be printed
     */
    public static void printList( List<Integer> values ){
        if (values.size() < SIZE_THRESHOLD) {
        for (int n : values) {
            System.out.printf("%4d", n);
        }//for
        System.out.println() ; 
        }//if
        else{
            for (int n : values ) {
                System.out.printf("%4d\n", n);
            }//for
        }//else
    }//printList (List<Integer>)
        
    /**
     * This method uses a sequential search to find a single integer within a list.
     * If the integer is located within the list, the search will return the index 
     * of it's first occurrence within the list. If the integer is not contained
     * within the list, this method will return -1.
     * @param values the list to be searched
     * @param target the integer to be found
     * @return the index at which the target first occurs
     */
    public static int sequentialSearch (List<Integer> values, int target) {
        int result = -1; 
        int index = 0;
        while(index < values.size() && result < 0) {
            if(target == values.get(index)) {
                result = index;
            }//if
            index = index + 1;
        }//while
        return result;
    }//sequentialSearch
    
    /**
     * This method uses a binary search to find a single integer within a list.
     * If the integer is located within the list, the search will return the index 
     * of it's first occurrence within the list. If the integer is not contained
     * within the list, this method will return -1.
     * @param values the list to be searched
     * @param target the integer to be found
     * @return the index at which the target first occurs
     */
    public static int binarySearch( List<Integer> values, int target ) {
        int result = -1;
        int lo = 0;
        int hi = values.size() - 1;
        while( lo<hi && result < 0 ){
            int mid = (lo+hi)/2;
            if(target == values.get(lo)){
                result = mid;
            }//if
            else if(target == values.get(mid)){
                result = mid;
            }//else if
            else if(target == values.get(hi)) {
                result = hi;
            }// elseif
            else if(target < values.get(mid) ) {
                hi = mid - 1;
            }
            else lo = mid + 1;
        }//while
        return result;
    }

    
    /**
     * This method uses a selection sort to sort a list of integers from low to high.
     * It requires an input list, and writes over that input list. A selection 
     * sort functions by first searching for the minimum value in a list, followed by
     * the next smallest value, and so on.
     * @param values the list to be sorted
     */
    public static void selectionSort(List<Integer> values) {
        for (int step = 0; step < values.size() - 1; step++) {
            int min_idx = step;
            for (int i = step + 1; i < values.size(); i++) {
                if (values.get(i) < values.get(min_idx)) {
                    min_idx = i;
                }//if
            }//for
        int temp = values.get(step);
        values.set(step, values.get(min_idx));
        values.set(min_idx, temp);
        }//for
    }//selectionSort

    /**
     * This method uses an insertion sort to sort a list of integers from low to high.
     * It requires only an input list, and writes over that input list. An insertion 
     * sort functions by comparing the first and second values of a list, followed by
     * the second and third, continuing as such until the list is fully sorted.
     * @param values the list to be sorted
     */
    public static void insertionSort(List<Integer> values) {
        for (int step = 1; step < values.size(); step++) {
            int key = values.get(step);
            int j = step - 1;
            while (j >= 0 && key < values.get(j)) {
                values.set(j+1, values.get(j));
                --j;
            }//while
      values.set(j + 1,key);
    }//for
  }//insertionSort

    /**
     * This method is used to merge the partial lists created during a merge sort.
     * (from leontabak)
     * @param values the list the partial lists originate from
     * @param prefixStart the beginning of the first half-list
     * @param suffixStart the beginning of the second half-list
     * @param suffixEnd the end of the second half-list
     */
    public static void merge(List<Integer> values, int prefixStart,
            int suffixStart, int suffixEnd) {
        List<Integer> temp = new ArrayList<>();

        int i = prefixStart;
        int j = suffixStart;

        while (i < suffixStart && j < suffixEnd) {
            if (values.get(i) < values.get(j)) {
                temp.add(values.get(i));
                i++;
            } // if
            else {
                temp.add(values.get(j));
                j++;
            } // else
        } // while

        while (i < suffixStart) {
            temp.add(values.get(i));
            i++;
        } // while

        while (j < suffixEnd) {
            temp.add(values.get(j));
            j++;
        } // while

        i = prefixStart;
        for (int index = 0; index < temp.size(); index++) {
            values.set(i, temp.get(index));
            i++;
        } // for
    } // merge( List<Integer>, int, int )
    
    /**
     * This method sorts a list of integers with a merge sort.
     * It requires a single input list, and writes over it. A merge sort
     * works by creating several smaller lists and sorting them individually.
     * (from leontabak)
     * @param values the list to be sorted
     */
    public static void mergeSort(List<Integer> values) {
        for (int stepSize = 2; stepSize < values.size(); stepSize *= 2) {
            for (int i = 0; i < values.size(); i += stepSize) {
                int prefixStart = i;
                int suffixStart = i + stepSize / 2;
                int suffixEnd = Math.min(values.size(), i + stepSize);
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // for
            if (stepSize > values.size() / 2) {
                int prefixStart = 0;
                int suffixStart = stepSize;
                int suffixEnd = values.size();
                merge(values, prefixStart, suffixStart, suffixEnd);
            } // if
            //printList(values);
        } // for
    } // mergeSort( List<Integer> )
    
    /**
     * This method generates lists and utilizes all the searches and sorts within the class.
     * 
     * @param a 
     */
    public static void main( String a[] ) {
        List<Integer> values = makeList( 12 );
        System.out.println( "Searching and sorting algorithms" );
        System.out.println("Before Sort");
        printList(values);
        selectionSort(values);
        System.out.println("Selection Sort");
        printList(values);
        values = makeList( 12 );
        System.out.println("Before Sort");
        printList(values);
        insertionSort(values);
        System.out.println("Insertion Sort");
        printList(values);
        values = makeList( 12 );
        System.out.println("Before Sort");
        printList(values);
        mergeSort(values);
        System.out.println("Merge Sort");
        printList(values);
        System.out.println("Sequential Search");
        int sIndex = sequentialSearch(values, 39);
        System.out.println("index = " + sIndex);
        System.out.println("Binary Search");
        int bIndex = binarySearch(values, 27);
        System.out.println("index = " + bIndex);
         // TO-DO: Add code that tests the searching and sorting
        // methods.
        
    } // main( String [] )
} // SearchAndSort
