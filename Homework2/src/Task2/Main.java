package Task2;

/*›
Task2
    [3, 4, 2, 7], 10 -> [3, 7] - вывести пару именно в скобках, которые дают сумму - 10
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        final int[] inputArray = {3, 4, 2, 7};
        final int countElements = 2;
        final int targetSum = 10;

        Optional<int[]> optional = getSumTerms(inputArray, countElements, targetSum);
        optional.ifPresent(ints -> System.out.println(Arrays.toString(ints)));

        assertGetSumTerms();
    }

    public static Optional<int[]> getSumTerms(int[] inputArray, int countElements, int targetSum) {
        return getCombinationsList(inputArray, countElements)
                .stream()
                .filter(array -> IntStream.of(array).sum() == targetSum)
                .findFirst();
    }

    public static List<int[]> getCombinationsList(int[] inputArray, int countElements) {
        int inputArrayLength = inputArray.length;
        int[] emptyArray = new int[countElements];
        List<int[]> pairList = new ArrayList<>();
        combinations(pairList, inputArray, inputArrayLength, countElements, 0, emptyArray, 0);
        return pairList;
    }

    public static void combinations(List<int[]> pairList, int[] inputArray, int inputArrayLength, int countElements,
                                    int arrayIndex, int[] emptyArray, int x) {
        if (arrayIndex == countElements) {
            int[] arr = new int[countElements];
            System.arraycopy(emptyArray, 0, arr, 0, countElements);
            pairList.add(arr);
            return;
        }
        if (x >= inputArrayLength)
            return;
        emptyArray[arrayIndex] = inputArray[x];
        combinations(pairList, inputArray, inputArrayLength, countElements, arrayIndex + 1, emptyArray, x + 1);
        combinations(pairList, inputArray, inputArrayLength, countElements, arrayIndex, emptyArray, x + 1);
    }

    public static void assertGetSumTerms() {
        final int[] array = {5, 7, 4, 9, 1};
        final int[] templateArray = {4, 9};
        final int countElements = 2;
        final int targetSum = 13;
        final int[] resultArray = getSumTerms(array, countElements, targetSum).orElse(null);
        assert Arrays.equals(templateArray, resultArray) : "Incorrect array!";
    }
}