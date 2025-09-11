package com.paintingscollectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaintingsCollectorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaintingsCollectorsApplication.class, args);


        System.out.println(binaryS(new int[] {1,2,3,4,5,6,7}, 46));

    }

    public static int binaryS(int[] arr, int number) {

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;

            if (number < arr[middle]) {
                high = middle - 1;
            } else if (number > arr[middle]) {
                low = middle + 1;
            } else {
                return arr[middle];
            }
        }


        return -1;
    }
}
