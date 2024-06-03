package Sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
/*
* Обычно константа игнорируется, потому что если два алгоритма имеют разное время «О-большое~, она роли не играет
* Однако в некоторых случаях константа может иметь значение. Один из примеров такого рода - быстрая сортировка и сортировка слиянием.
* У быстрой сортировки константа меньше, чем у сортировки слиянием, поэтому, несмотря на то Что оба алгоритма характеризуются временем О(п log п), быстрая сортировка работает быстрее. А на практике быстрая сортировка работает быстрее, потому что средний случай встречается намного чаще худшего.
*  */
public class EffectiveSorting {
    /**
     * Сортировка слиянием - эффективную и устойчивую сортировку с гарантированной временной сложностью.
     *
     *  <p>Эффективность алгоритма:
     *  <ul>
     *      <li>Сложность по времени: O(n*log n) как в среднем, так и в худшем случае.</li>
     *      <li>Пространственная сложность (по памяти): O(n).</li>
     *  </ul>
     */
    public static void mergeSort(int[] arr){
        if(arr.length > 1) {
            int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        } else return;
    }

    private static void merge(int[] result, int[] left, int[] right){
        int i = 0, j = 0, k = 0;
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                result[k++] = left[i++];
            }else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    /**
     * Быстрая сортировка - одна из самых эффективных сортировок в совокупности по памяти и времени
     *
     *  <p>Эффективность алгоритма:
     *  <ul>
     *      <li>Сложность по времени: O(n*log n) - в среднем случае; O(n^2) - в худшем случаем (возникает редко, например, когда массив уже отсортирован или все элементы одинаковые).</li>
     *      <li>Пространственная сложность (по памяти): O(log n).</li>
     *  </ul>
     */
    public static void quick(int[] arr, int left, int right){
        if(left >= right){return;}
        int pivot = arr[(left + right) / 2];
        int i = left, j = right;
        while (i <= j){
            while(arr[i] < pivot){i++;}
            while(arr[j] > pivot){j--;}
            if(i <= j){
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        quick(arr, left, j);
        quick(arr, i, right);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
