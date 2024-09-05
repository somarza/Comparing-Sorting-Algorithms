package Q1;

import java.util.*;

public class SeabarTestingSortingMethods {

    // Generic method to perform selection sort on an array of comparable elements
    public static <T extends Comparable<? super T>>long selectionSort(T[]a){
        // Record the start time before sorting
        long startTime = System.nanoTime();
        // Selection Sort Algorithm: selects the minimum element from the unsorted part over and over again of the array and places it at the beginning
        for (int i = 0; i < a.length - 1; i++) {//iterates over each element of the array.
            // Assume the current index is the minimum
            int minIndex = i;
            // Find the index of the minimum element in the unsorted part of the array
            for (int j = i + 1; j < a.length; j++) {//finds index of the minimum element in the unsorted part.
                if (a[j].compareTo(a[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the current element (place it in its sorted position)
            T temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
        // Record the end time after sorting
        long endTime = System.nanoTime();
        // Return the time elapsed during sorting in milliseconds
        return (endTime - startTime);

    }
    public static <T extends Comparable<? super T>>long bubbleSort(T[]a){
        // Record the start time before sorting
        long startTime = System.nanoTime();
        // Bubble Sort Algorithm: repeatedly steps through the array, comparing the adjacent elements, and then swaps them if they're in the wrong order.
        //outer for loop: controls the number of iterations, reducing the number of elements to be compared and swapped
        for (int i=1; i< a.length; i++){
            //inner loop: iterates over the unsorted part of the array.
            for (int j=0; j<a.length-i;j++){
                //compares adjacent elements,  if out of order, swaps them.
                if (a[j].compareTo(a[j+1])>0){
                    T temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        // Record the end time after sorting
        long endTime = System.nanoTime();
        // Return the time elapsed during sorting
        return endTime - startTime;
    }
    public static <T extends Comparable<? super T>>long insertionSort(T[]a){
        long startTime = System.nanoTime();
        //Insertion sort algorithm: builds the sorted array one element at a time.
        //Iterates over the array starting from the second element.
        for(int i=1; i<a.length; i++){
            T key = a[i];//Selects the key element to be inserted at its correct position.
            int j = i-1;

            // Move the elements greater than key to one position ahead of their current position
            while (j>= 0 && a[j].compareTo(key)>0){
                a[j+1] = a[j];
                j = j-1;
            }
            a[j+1] = key;//Places the key in its sorted position in the array.
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    //Merge Sort divides and conquers. It keeps dividing the unsorted list with n elements, until it reaches n single element sub-lists.
    // Then it merges the sub lists together two have two sorted lists until eventually the elements are fully merged together in order.
    public static <T extends Comparable<? super T>>long mergeSort(T[]S){
        long startTime = System.nanoTime();
        int n = S.length;
        if (n < 2) {
            return 0;} //default value as its a long
        // array is trivially sorted

        // divide
        int mid = n/2;
        T[] S1 = Arrays.copyOfRange(S, 0, mid);   // copy of first half
        T[] S2 = Arrays.copyOfRange(S, mid, n);   // copy of second half
        // conquer (with recursion)
        mergeSort(S1);                      // sort copy of first half
        mergeSort(S2);                      // sort copy of second half
        // merge sorted halves back into original
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
                S[i+j] = S1[i++];                     // copy ith element of S1 and increment i
            else
                S[i+j] = S2[j++];                     // copy jth element of S2 and increment j
        }
        long endTime = System.nanoTime(); return endTime - startTime;
    }
    //Quicksort sorts a sequence T using the recursive approach by dividing 2 elements at a time and conquering the sorted changes
    public static <T extends Comparable<? super T>> long quickSort(T[] s, int a, int b) {
        long startTime = System.nanoTime();
        if (a >= b) {
            return 0;
        }
        T pivot = s[b]; // making the pivot the last element in the array
        int left = a;
        int right = b - 1;
        while (left <= right) {
            while (left <= right && s[left].compareTo(pivot) < 0) {
                left++;
            }
            while (left <= right && s[right].compareTo(pivot) > 0) {
                right--;
            }
            if (left <= right) {
                // swap the s[right] and s[left]
                T temp = s[left];
                s[left] = s[right];
                s[right] = temp;
                left++;
                right--;
            }
        }
        // swap the s[right] and s[left], putting pivot into its final space
        T temp = s[left];
        s[left] = s[b];
        s[b] = temp;
        quickSort(s, a, left - 1);
        quickSort(s, left + 1, b);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long bucketSort(Integer[] a, int first, int last, int maxDigits) {
        long startTime = System.nanoTime();
        //Bucket Sort Algorithm: distributes elements into buckets based on their digits and then sorts each bucket individually.
        // Creates an array of Vector to represent buckets for each digit (0-9).
        Vector<Integer>[] bucket = new Vector[10];
        //instantiate each bucket(each digit place) and places elements from the array into respective buckets based on the current digit place.
        for (int i = 0; i < 10; i++)
            bucket[i] = new Vector<>();
        for (int i = 0; i < maxDigits; i++) {
            //clear the Vector buckets
            for (int j = 0; j < 10; j++) {
                bucket[j].removeAllElements();
            }
            //Placing a[index] at end of bucket[digit]

            for (int index = first; index <= last; index++) {
                Integer digit = findDigit(a[index], i);
                bucket[digit].add(a[index]);         }
            //placing all the buckets back into the array
            int index = 0;
            for (int m = 0; m < 10; m++) {
                for (int n = 0; n < bucket[m].size(); n++) {
                    a[index++] = bucket[m].get(n);
                }
            }
        }long endTime = System.nanoTime();
        return endTime-startTime;
    } //The following method extracts the ith digit from a decimal number
    public static Integer findDigit(int number, int i) {
        //Extracts the ith digit from a decimal number.
        int target = 0;//Initializes a variable target to store the extracted digit.

        // this loop iterates i+1 times through the digits of the number until it reaches the desired position i.
        for (int k = 0; k <= i; k++) {
            target = number % 10;//Extracts the last digit of the current value of number and is the target
            number = number / 10;//Updates the value of number by removing its last digit
        }
        return target;//Returns the extracted digit
    }

    public static void myHeader(int LabNum, int QuestionNum){
        System.out.println("================================================================\nLab Exercise "+ LabNum+", Q"+QuestionNum+"\nPrepared By: Seabar Omarzadeh\nStudent Number: 251277521\nGoal of this Exercise: \n" +
                "To implement and compare the performance of various sorting algorithms by measuring their execution times.\n================================================================");
    }

    //method to display footer info
    public static void myFooter(int LabNum,int QuestionNum) {//public method footer to be accessed by classes
        System.out.println("\n================================================================\nCompletion of Lab Exercise "+ LabNum +", Q"+QuestionNum+ " is successful! \nSigning off- Seabar\n================================================================\n");//monitor displays signing off
    }
    public static void main(String[] args) {
        //call header method
        myHeader(6,1);
        System.out.println("Testing execution time of different methods for sorting 5 random numbers:\n");
        //declare integer with a preset size
        int sz = 50000;
        Integer[] arr = new Integer[sz];
        //create backup Integer type array with same pre set size
        Integer[] backup = new Integer[sz];
        //populate the first array with random values from 13 to 93 inclusive
        for(int i=0;i<arr.length;i++){
            arr[i] = (int)(13+(Math.random() * 79));
        }
        System.arraycopy(arr,0,backup,0,arr.length); //copying arr to back up

        //Collections class
        List<Integer> arrList = Arrays.asList(arr);
        //System.out.println("Unsorted list: "+ arrList.toString());
        long start = System.nanoTime();
        Collections.sort(arrList); //sorting the array using Collection classes sort method//
        long end = System.nanoTime();
        // The time is formatted in milliseconds
        System.out.printf("Collections' Sorting Time: %.2f",(double)(end-start)/1_000_000);//The casting to double to produce a result with decimal precision.
        //System.out.println("\nThe sorted list using Collections sort method: "+arrList.toString()+"\n");

        //Selection Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Selection Sort Time: %.2f milliseconds",(double)selectionSort(arr)/1_000_000);
        //System.out.println("\nthe sorted list using selection-sort: "+arrList.toString()+"\n");

        //Bubble Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Bubble Sort Time: %.2f milliseconds",(double)bubbleSort(arr)/1_000_000);
        //System.out.println("\nthe sorted list using bubble-sort: "+arrList.toString()+"\n");

        //Insertion Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Insertion Sort Time: %.2f milliseconds",(double)insertionSort(arr)/1_000_000); //casting to double and converting to milliseconds//
        //System.out.println("\nthe sorted list using insertion-sort: "+arrList.toString()+"\n");

        //Merge Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Merge Sort Time: %.2f milliseconds",(double)mergeSort(arr)/1_000_000); //casting to double and converting to milliseconds//
// System.out.println("\nthe sorted list using merge-sort: "+arrList.toString()+"\n");

        //Quick Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Quick Sort Time: %.2f milliseconds",(double)quickSort(arr,0,arr.length-1)/1_000_000); //casting to double and converting to milliseconds//
// System.out.println("\nthe sorted list using quick-sort: "+arrList.toString()+"\n");

        //Bucket Sorting
        System.arraycopy(backup,0,arr,0,backup.length); //copying back to original unsorted array
        //System.out.println("Unsorted List: "+arrList.toString());
        System.out.printf("\nMy Bucket Sort Time: %.2f milliseconds",(double)bucketSort(arr,0,arr.length-1,2)/1_000_000); //casting to double and converting to milliseconds//
        //System.out.println("\nthe sorted list using bucket-sort: "+arrList.toString()+"\n");

        myFooter(6,1);
        }
    }

