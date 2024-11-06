package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class QSort {

    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        List<List<T>> stage = new ArrayList<>(collection.size());
        var iter = collection.iterator();
        for (int i = 0; i < collection.size(); i++) {
            stage.add(List.of(iter.next()));
        }
        int nextStageSize = collection.size();
        int stageCount = log2(collection.size());
        for (int j = 0; j < stageCount; j++) {
            nextStageSize = getNextStageSize(nextStageSize);
            List<List<T>> nextStage = createNextStage(nextStageSize);
            for (int i = 0; i < nextStageSize; i++) {
                if (i * 2 + 1 == stage.size()) {
                    nextStage.add(stage.get(stage.size() - 1));
                    continue;
                }
                nextStage.add(merge(stage.get(i * 2), stage.get(i * 2 + 1), comparator));
            }
            stage = nextStage;
        }
        assert stage.size() == 1;
        return stage.get(0);
    }

    private static <T> List<T> merge(List<T> firstList, List<T> secondList, Comparator<T> comparator) {
        int firstIndx = 0;
        int secondIndx = 0;
        var result = new ArrayList<T>(firstList.size() + secondList.size());
        while (firstIndx < firstList.size() || secondIndx < secondList.size()) {
            if (firstIndx == firstList.size()) {
                result.addAll(secondList.subList(secondIndx, secondList.size()));
                secondIndx = secondList.size();
                continue;
            }
            if (secondIndx == secondList.size()) {
                result.addAll(firstList.subList(firstIndx, firstList.size()));
                firstIndx = firstList.size();
                continue;
            }
            var first = firstList.get(firstIndx);
            var second = secondList.get(secondIndx);
            if (comparator.compare(first, second) < 0) {
                result.add(first);
                firstIndx++;
            } else {
                result.add(second);
                secondIndx++;
            }
        }
        return result;
    }

    private static <T> List<List<T>> createNextStage(int nextStageSize) {
        return new ArrayList<>(nextStageSize);
    }

    private static int getNextStageSize(int stageSize) {
        return (stageSize + 1) / 2;
    }

    private static int log2(int size) {
        int count = 0;
        while (size > 0) {
            size /= 2;
            count++;
        }
        return count;
    }
}
