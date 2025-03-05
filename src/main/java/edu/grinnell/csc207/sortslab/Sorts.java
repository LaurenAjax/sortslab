package edu.grinnell.csc207.sortslab;

import java.util.Arrays;

/**
 * A collection of sorting algorithms over generic arrays.
 */
public class Sorts {
    /**
     * Swaps indices <code>i</code> and <code>j</code> of array <code>arr</code>.
     * @param <T> the carrier type of the array
     * @param arr the array to swap
     * @param i the first index to swap
     * @param j the second index to swap
     */
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Sorts the array according to the bubble sort algorithm:
     * <pre>
     * [ unprocessed | i largest elements in order ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Sorts the array according to the selection sort algorithm:
     * <pre>
     * [ i smallest elements in order | unprocessed ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min].compareTo(arr[j]) > 0) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    /**
     * Sorts the array according to the insertion sort algorithm:
     * <pre>
     * [ i elements in order | unprocessed ] 
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j + 1].compareTo(arr[j]) < 0) {
                    swap(arr, j + 1, j);
                }
            }
        }
    }
    
    /**
     * Merges two sorted sides of an array into one sorted whole.
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     * @param left the left index to start at
     * @param mid the middle index of the array, divider between two sorted sides
     * @param right the right index to end at
     */
    public static <T extends Comparable<? super T>> void merge(T[] arr, int left, int mid, int right) {
        T[] leftArr = Arrays.copyOfRange(arr, left, mid);
        T[] rightArr = Arrays.copyOfRange(arr, mid + 1, right);
        
        int leftInd = 0;
        int rightInd = 0;
        int sortedInd = left;
        
        while (leftInd < leftArr.length && rightInd < rightArr.length) {
            if (leftArr[leftInd].compareTo(rightArr[rightInd]) <= 0) {
                arr[sortedInd++] = leftArr[leftInd++];
            } else {
                arr[sortedInd++] = rightArr[rightInd++];
            }
        }
        
        while (leftInd < leftArr.length) {
            arr[sortedInd++] = leftArr[leftInd++];
        }
        
        while (rightInd < rightArr.length) {
            arr[sortedInd++] = rightArr[rightInd++];
        }
    }
    
    /**
     * Sorts each half of the array and then merges them (main execution of merge sort)
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     * @param left the left index to start at
     * @param right the right index to end at
     */
    public static <T extends Comparable<? super T>> void sort(T arr[], int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }

    /**
     * Sorts the array according to the merge sort algorithm:
     * <pre>
     * [ sorted | sorted ] -> [ sorted ]
     * </pre>
     * @param <T> the carrier type of the array
     * @param arr the array to sort
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        sort(arr, 0, arr.length);
        
        /*T[] arr1 = (T[]) new Object[arr.length / 2];
        T[] arr2 = (T[]) new Object[arr.length - (arr.length / 2)];
        System.arraycopy(arr, 0, arr1, 0, arr1.length);
        System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);*/
    }

    /**
     * Sorts the array according to the quick sort algorithm:
     * <pre>
     * []
     * </pre>
     * @param <T>
     * @param arr
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        // TODO: fill me in!
    }
    
    /**
     * 
     * @param <T> the carrier type of the array
     * @param value the value to find
     * @param arr the array to search, assumed to be sorted
     * @param lo the lower bound of indices to search (inclusive)
     * @param hi the upper bound of indices to search (exclusive)
     * @return the index if <code>value</code> T <code>arr</code> or 
     * <code>-1</code> if <code>value</code> is not in <code>arr</code>
     */
    public static <T extends Comparable<? super T>> int binarySearch(T value, T[] arr, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        if (lo >= hi) {
            return -1;
        }
        if (arr[mid].compareTo(value) < 0) {
            return binarySearch(value, arr, mid + 1, hi);
        } else if (arr[mid].compareTo(value) > 0) {
            return binarySearch(value, arr, lo, mid);
        } else {
            return mid;
        }
    }
}
