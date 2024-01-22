package org.example.pubsub.average;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RandomNumbersAverage {
    private static final int count = 1000000;

    public static double average() {
        AverageOfRandoms avg = new AverageOfRandoms(0, count);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(avg);

        return avg.getAverage();
    }

    private static class AverageOfRandoms extends RecursiveAction {
        private static final int LIMIT = 100000;

        private int start;
        private int end;
        private double sum = 0;


        private Random rd = new Random();

        public AverageOfRandoms(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= LIMIT) {
                for (int i = start; i < end; i++) {
                    int number = (rd.nextInt() % 3 + 3) % 3;
                    sum += number;
                }
            } else {
                int mid = (start + end) / 2;

                AverageOfRandoms first = new AverageOfRandoms(start, mid);
                AverageOfRandoms second = new AverageOfRandoms(mid, end);

                invokeAll(first, second);
                sum = first.sum + second.sum;
            }
        }

        public double getAverage() {
            return sum / count;
        }
    }

}
