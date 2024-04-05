package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void testBinarySearch_ReturnTrue_RandomArr() {
        Random random = new Random();
        int size = random.nextInt(10000) + 1;
        int[] myArr = new int[size];
        for(int i = 0; i < size; i++) {
            myArr[i] = random.nextInt();
        }
        Arrays.sort(myArr);

        int target = myArr[random.nextInt(size)];
        assertTrue(Main.binarySearch(myArr, target), "Элемент должен быть найден в массиве");
    }

    @Test
    void testBinarySearch_ReturnFalse_RandomArr() {
        Random random = new Random();
        int size = random.nextInt(9) + 1;
        int[] myArr = new int[size];
        for(int i = 0; i < size; i++) {
            myArr[i] = random.nextInt(10);
        }
        Arrays.sort(myArr);

        int target = 10;
        assertFalse(Main.binarySearch(myArr, target), "Элемент не должен быть найден в массиве");
    }

    @Test
    void testBinarySearch_ThrowException_IfArrayIsNull(){
        assertThrows(IllegalArgumentException.class, () -> Main.binarySearch(null, 1),
                "Должно быть выброшено исключение для null массива");

        assertThrows(IllegalArgumentException.class, () -> Main.binarySearch(new int[]{}, 1),
                "Должно быть выброшено исключение для пустого массива");
    }

    @Test
    void  testBinarySearch_ReturnTrue_IfSizeArrayIsBig(){
        int size = 10_000_000;
        int[] myArr = new int[size];
        for (int i = 0; i < size; i++) {
            myArr[i] = i;
        }

        assertTrue(Main.binarySearch(myArr, 10_000_000 - 1), "Элемент должен быть найден в массиве, проверка на переполнение");
    }
}