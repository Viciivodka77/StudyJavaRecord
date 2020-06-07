package StudyDay02;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < arr.length ; i++){
            arr[i] = input.nextInt();
        }
        //输出
        System.out.println("原始数组:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //冒泡排序
        for (int i = 0; i < arr.length-1 ; i++) {
            for (int j = 0; j < arr.length-1-i ; j++) {
                if (arr[j] > arr [j+1]){
                    int temp = arr[j];
                    arr[j] = arr [j+1];
                    arr[j+1] = temp;
                }
            }
        }
        //输出
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
