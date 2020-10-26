package com.packt.modern.api;

import com.packt.modern.api.aop.TimeMonitor;
import org.springframework.stereotype.Component;

/**
 * @author  : github.com/sharmasourabh
 * @project : Chapter02 - Modern API Development with Spring and Spring Boot
 * @created : 10/20/2020, Tuesday
 **/
@Component
public class SortUtil {

  @TimeMonitor
  public void bubbleSort(int[] list) {
    int n = list.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (list[j] > list[j + 1]) {
          int temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
        }
      }
    }
  }

  @TimeMonitor
  public void insertionSort(int[] list) {
    int n = list.length;
    for (int i = 1; i < n; ++i) {
      int key = list[i];
      int j = i - 1;
      while (j >= 0 && list[j] > key) {
        list[j + 1] = list[j];
        j = j - 1;
      }
      list[j + 1] = key;
    }
  }

  @TimeMonitor
  public void selectionSort(int[] list) {
    int n = list.length;
    for (int i = 0; i < n - 1; i++) {
      int min_idx = i;
      for (int j = i + 1; j < n; j++) {
        if (list[j] < list[min_idx]) {
          min_idx = j;
        }
      }
      int temp = list[min_idx];
      list[min_idx] = list[i];
      list[i] = temp;
    }
  }

  @TimeMonitor
  public void mergeSort(int[] list) {
    mergeSorting(list, 0, list.length - 1);
  }

  private void mergeSorting(int[] list, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;
      mergeSorting(list, l, m);
      mergeSorting(list, m + 1, r);
      merge(list, l, m, r);
    }
  }

  private void merge(int list[], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[] = new int[n1];
    int R[] = new int[n2];

    for (int i = 0; i < n1; ++i) {
      L[i] = list[l + i];
    }
    for (int j = 0; j < n2; ++j) {
      R[j] = list[m + 1 + j];
    }
    int i = 0, j = 0;
    int k = l;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        list[k] = L[i];
        i++;
      } else {
        list[k] = R[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      list[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      list[k] = R[j];
      j++;
      k++;
    }
  }

  @TimeMonitor
  public void quickSort(int[] list) {
    quickSorting(list, 0, list.length - 1);
  }

  private void quickSorting(int[] list, int low, int high) {
    if (low < high) {
      int pi = partition(list, low, high);
      quickSorting(list, low, pi - 1);
      quickSorting(list, pi + 1, high);
    }
  }

  int partition(int list[], int low, int high) {
    int pivot = list[high];
    int i = (low - 1);
    for (int j = low; j < high; j++) {
      if (list[j] < pivot) {
        i++;
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
      }
    }

    int temp = list[i + 1];
    list[i + 1] = list[high];
    list[high] = temp;

    return i + 1;
  }
}
