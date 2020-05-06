package cn.lxc.paradise.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Practice1 {

    private static void quickSort(int[] arr, int begin, int end) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (begin >= end) {
            return;
        }
        int ref = arr[begin];
        int i = end;
        int j = begin;
        int tmp = 0;
        a:while (i > j) {
            if (arr[i] < ref) {
                b:while (j < i) {
                    if (arr[j] > ref) {
                        tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                        break;
                    }
                    j++;
                }
                continue a;
            }
            i--;
        }
        tmp = arr[i];
        arr[i] = arr[begin];
        arr[begin] = tmp;
        quickSort(arr, begin, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static void main(String[] args) {
//        int[] arr = {6,1,2,7,9,3,4,5,10,8};
        int[] arr = {5,6,3,4,1};
        quickSort(arr, 0, arr.length -1);
        System.out.println(Arrays.toString(arr));
    }

}
