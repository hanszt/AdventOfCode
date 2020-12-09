package com.sogeti.codingchallenge.day09;

import java.util.ArrayList;
import java.util.List;

public class Part2EncodingError extends Day09Challenge {

    public Part2EncodingError() {
        super("Encoding Error part 2",
                "you must find a contiguous set of at least two numbers " +
                        "in your list which sum to the invalid number from step 1. " +
                        "To find the encryption weakness, add together the smallest and largest number in this contiguous range");
    }

    @Override
    protected long solveByXmasList(List<Long> longs) {
        long resultStep1 = findFirstNumberNotSumOfTwoIntegersInPreamble(longs);
        List<Long> contiguousSumList = findSumList(longs, resultStep1);
        return findSumMinAndMaxNumber(contiguousSumList);
    }

    private List<Long> findSumList(List<Long> longs, long ref) {
        long sum = 0;
        List<Long> sumList = new ArrayList<>();
        for (int i = 0; i < longs.size(); i++) {
            for (int j = i; j < longs.size(); j++) {
                sumList.add(longs.get(j));
                sum += longs.get(j);
                if (sum == ref) return sumList;
            }
            sumList.clear();
            sum = 0;
        }
        return sumList;
    }

    private long findSumMinAndMaxNumber(List<Long> list) {
        long min = list.stream().reduce(Long::min).orElse(0L);
        long max = list.stream().reduce(Long::max).orElse(0L);
        return min + max;
    }

    @Override
    String getMessage(long sum) {
        return String.format("%d", sum);
    }
}
