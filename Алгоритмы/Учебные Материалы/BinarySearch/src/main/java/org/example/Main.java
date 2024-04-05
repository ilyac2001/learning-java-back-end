package org.example;

/**
 * Класс предоставляет метод для выполнения двоичного поиска в отсортированном массиве.
 */
public class Main {

    /**
     * Точка входа в программу. Демонстрирует использование бинарного поиска.
     *
     * @param args Аргументы командной строки не используются.
     */
    public static void main(String[] args) {
        int[] myArray = {1, 2, 3};
        int target = 0;
        boolean result = binarySearch(myArray, target);
        System.out.print(result);
    }

    /**
     * Выполняет двоичный поиск целевого значения в отсортированном массиве.
     *
     * @param array Отсортированный массив, в котором выполняется поиск.
     * @param target Целевое значение для поиска.
     * @return {@code true}, если целевое значение найдено в массиве, иначе {@code false}.
     */
    public static boolean binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть null или пустым");
        }

        int leftPointer = 0;
        int rightPointer = array.length - 1;

        while (leftPointer <= rightPointer) { //подобный алгоритм может быть реализован рекурсивно: + чище, читабильнее; - менее производительный и мастабируемый
            int midPointer = leftPointer + (rightPointer - leftPointer) / 2; //такая формула избегает переполнения (leftPointer + rightPointer > Integer.MAX_VALUE)

            if (target == array[midPointer]) {
                return true;
            } else if (target < array[midPointer]) {
                rightPointer = midPointer - 1;
            } else {
                leftPointer = midPointer + 1;
            }
        }
        return false;
    }
}
