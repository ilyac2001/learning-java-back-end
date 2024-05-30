package Sorting;

import java.util.List;
/**
 * Простые - легки в реализации, но не самые эффективные
 *
 *  <p>Эффективность алгоритма:
 *  <ul>
 *      <li>Сложность по времени: O(n^2).</li>
 *      <li>Сложность по памяти: O(1).</li>
 *  </ul>
 */
public class SimpleSorting {
    private static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void bubble(@org.jetbrains.annotations.NotNull int[] arr){
        boolean swapped;
        for(int i = 0; i < arr.length; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped){break;} //выход из цикла означает, что массив уже отсортирован
        }
    }

    public static void selection(int[] arr){
        int indexMinValue;
        for(int i = 0; i < arr.length - 1; i++) {
            indexMinValue = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[indexMinValue]) {
                    indexMinValue = j;
                }
            }
            swap(arr, i, indexMinValue);
        }
    }

    public static void insertion(int[] arr) {
        for(int i = 1; i < arr.length; i++ ) {
            int saveValue = arr[i];
            int j = i - 1;
            while(j >= 0 && saveValue < arr[j]){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = saveValue;
        }
    }

    public static void insertion(List<Integer> list) {
        for(int i = 1; i < list.size(); i++) {
            int saveValue = list.get(i);
            int j = i - 1;
            while (j >= 0 && saveValue < list.get(j)){
                list.set(j + 1 , list.get(j));
                j--;
            }
            list.set(j + 1, saveValue);
        }
    }
}
