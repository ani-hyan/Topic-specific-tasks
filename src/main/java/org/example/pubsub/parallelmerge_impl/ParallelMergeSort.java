package org.example.pubsub.parallelmerge_impl;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void parallelMergeSort(T[] array) {
        SortTask<T> mainTask = new SortTask<>(array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask<T extends Comparable<T>> extends RecursiveAction {
        private T[] S;

        public SortTask(T[] S) {
            this.S = S;
        }

        @Override
        protected void compute() {
            if (S.length > 1) {
                int mid = S.length / 2;

                T[] S1 = Arrays.copyOfRange(S, 0, mid);
                T[] S2 = Arrays.copyOfRange(S, mid, S.length);

                SortTask<T> firstHalfTask = new SortTask<>(S1);
                SortTask<T> secondHalfTask = new SortTask<>(S2);

                invokeAll(firstHalfTask, secondHalfTask);

                merge(S1, S2, S);
            }
        }
    }

    private static <T extends Comparable<T>> void merge(T[] S1, T[] S2, T[] S) {
        int i = 0, j = 0;

        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0)) {
                S[i + j] = S1[i++];
            } else {
                S[i + j] = S2[j++];
            }
        }
    }
}
