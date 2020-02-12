/**
 * Implements various merge style algorithms.
 *
 * Last updated 2/8/2020.
 *
 * Completion time: 8 hours
 *
 * @author Brett Perry, Acuna, Sedgewick and Wayne
 * @verison 1.0, 11 February 2020
 */
import java.util.Random;

public class PerryMerging {

    /**
     * Entry point for sample output.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Queue q1 = new ListQueue(); q1.enqueue("T"); q1.enqueue("R"); q1.enqueue("O"); q1.enqueue("L"); q1.enqueue("E"); //contains E L O R T (front)
        Queue q2 = new ListQueue(); q2.enqueue("X"); q2.enqueue("S"); q2.enqueue("P"); q2.enqueue("M"); q2.enqueue("E"); q2.enqueue("A"); ///contains A E M P S X (front)
        Queue q3 = new ListQueue(); q3.enqueue(20); q3.enqueue(17); q3.enqueue(15); q3.enqueue(12); q3.enqueue(5); //contains 5, 12, 15, 17, 20 (front)
        Queue q4 = new ListQueue(); q4.enqueue(18); q4.enqueue(16); q4.enqueue(13); q4.enqueue(12); q4.enqueue(4); q4.enqueue(1); //contains 1, 4, 12, 13, 16, 18 (front)

        //Q1 - sample test cases
        Queue merged1 = mergeQueues(q1, q2);
        System.out.println(merged1.toString());
        Queue merged2 = mergeQueues(q3, q4);
        System.out.println(merged2.toString());

        //Q2 - sample test cases
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert isSorted(a);
        show(a);

        //Q3 - sample test cases
        String[] b = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        shuffle(b);
        show(b);

        shuffle(b);
        show(b);
    }
 /*******************************begin assignment******************************/


    /* 
     * Develop a static method that takes two queues of sorted items as
     * arguments and returns a queue that results from merging the queues 
     * into sorted order.
     */
    public static Queue mergeQueues(Queue q1, Queue q2) {
        //TODO: implement this.
        Queue result = new ListQueue();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (!less((Comparable) q1.front(), (Comparable) q2.front())) {
                result.enqueue(q1.dequeue());
            } else {
                result.enqueue(q2.dequeue());
            }
        }

        while (!q1.isEmpty()) {
            result.enqueue(q1.dequeue());
        }

        while (!q2.isEmpty()) {
            result.enqueue(q2.dequeue());
        }
        return result;
    }

    /*
     * Reimplement the mergesort algorithm to pass only arrays as parameters.  
     * The starting point will be the method 
     * public static void sort(Comparable[] a), which will start the recursive 
     * mergesort process. Plan to include a recursive helper method, 
     * public static Comparable[] mergesort(Comparable[] a), and a merge method, 
     * public static Comparable[] merge(Comparable[] a, Comparable[] b)
     */
    public static void sort(Comparable[] a) {
        //TODO: implement this.
        Comparable[] b = mergesort(a);

        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }

    //recursive helper method for public static void sort(Comparable[] a)
    public static Comparable[] mergesort(Comparable[] a) {
        if (a.length < 2)
            return a;

        int midPos = a.length / 2;
        Comparable[] firstHalf = new Comparable[midPos];
        Comparable[] secondHalf = new Comparable[a.length - midPos];

        for (int i = 0; i < midPos; i++) {
            firstHalf[i] = a[i];
        }

        for (int i = midPos, j = 0; i < a.length; i++, j++) {
            secondHalf[j] = a[i];
        }

        firstHalf = mergesort(firstHalf);
        secondHalf = mergesort(secondHalf);

        return a;
    }

    //merge method for public static void sort(Comparable[] a)
    public static Comparable[] merge(Comparable[] a, Comparable[] b) {

        Comparable[] result = new Comparable[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length || j < b.length) {
            if (i < a.length && k < b.length) {
                if (((Comparable) a[i]).compareTo(b[j]) < 0) {
                    result[k] = a[i];
                    k++;
                    j++;
                } else if (((Comparable) a[i]).compareTo(b[j]) > 0) {
                    result[k] = b[j];
                    k++;
                    j++;
                } else {
                    result[k] = a[i];
                    k++;
                    i++;

                    result[k] = b[j];
                    k++;
                    j++;
                }
            } else if (i < a.length && j >= b.length) {
                result[k] = a[i];
                k++;
                j++;
            } else if (j < b.length && i >= a.length) {
                result[k] = b[j];
                k++;
                j++;
            }
        }
        return result;
    }

    //shuffle the array
    public static void shuffle(Object[] a) {
        //TODO: implement this.
        shuffle(a, 0, a.length-1);
    }

    //recursively shuffle the array
    private static void shuffle(Object[] a, int first, int last) {

        if (first > last)
            return;

        int middle = (first + last) / 2;
        int randIndex = (new Random()).nextInt(a.length);

        Object middleElement = a[middle];
        a[middle] = a[randIndex];
        a[randIndex] = middleElement;

        shuffle(a, first, middle - 1);
        shuffle(a, middle + 1, last);
    }

/*******************************end assignment*********************************/

    //sorting helper from text
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //sorting helper from text
    private static void show(Comparable[] a) {
        for (Comparable a1 : a)
            System.out.print(a1 + " ");

        System.out.println();
    }

    //sorting helper from text
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;

        return true;
    }
}
