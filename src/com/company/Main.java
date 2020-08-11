package com.company;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 1, 8, 4, 2, 9, 7, 6, 0};
        System.out.println("Initial array \n" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("After Merge sorting \n" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("After Quick sorting \n" + Arrays.toString(arr));
        countingSort(arr, 9);
        System.out.println("After Counting sorting \n" + Arrays.toString(arr));
        shellaSort(arr);
        System.out.println("After Shella sorting \n" + Arrays.toString(arr));
        shuttleSort(arr);
        System.out.println("After Shuttle sorting \n" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("After Bubble sorting \n" + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("After Insertion sorting \n" + Arrays.toString(arr));
        selectionSort(arr);
        System.out.println("After Selection sorting \n" + Arrays.toString(arr));
    }
    public static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }
    public static void mergeSort(int[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }
    static void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;
        // создаем временные подмассивы
        int leftArray[] = new int[lengthLeft];
        int rightArray[] = new int[lengthRight];
        // копируем отсортированные массивы во временные
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left + i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid + i + 1];
        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;
        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
    public static int[] countingSort(int[] array, int maxValue) {
        // Массив со "счётчиками" размером от 0 до максимального значения
        int numCounts[] = new int[maxValue + 1];
        // В соответствующей ячейке (индекс = значение) увеличиваем счётчик
        for (int num : array) {
            numCounts[num]++;
        }
        // Подготавливаем массив для отсортированного результата
        int[] sortedArray = new int[array.length];
        int currentSortedIndex = 0;
        // идём по массиву со "счётчиками"
        for (int n = 0; n < numCounts.length; n++) {
            int count = numCounts[n];
            // идём по количеству значений
            for (int k = 0; k < count; k++) {
                sortedArray[currentSortedIndex] = n;
                currentSortedIndex++;
            }
        }
        return sortedArray;
    }
    public static void shellaSort(int[] array) {
        // Высчитываем промежуток между проверяемыми элементами
        int gap = array.length / 2;
// Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
    }
    public static void shuttleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (array[z] < array[z - 1]) {
                        swap(array, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        boolean needsInteration = true;
        while(needsInteration) {
            needsInteration = false;
            for (int i = 1; i < arr.length; i++) {
                if(arr[i - 1] > arr[i]) {
                    swap(arr, arr[i - 1], arr[i]);
                    needsInteration = true;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for(int left = 0; left < arr.length; left++) {
            int minInd = left;
            for(int i = left; i < arr.length; i++) {
                if(arr[i] < arr[minInd]) {
                    minInd = i;
                }
            }
            swap(arr, left, minInd);
        }
    }

    public static void insertionSort(int[] arr) {
        for(int left = 0; left < arr.length; left++) {
            int value = arr[left];
            int i = left - 1;
            for(; i >= 0; i--) {
                if(value < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = value;
        }
    }
    public static void swap(int[] array, int num1, int num2) {
        int temp = array[num1];
        array[num1] = array[num2];
        array[num2] = temp;
    }
}