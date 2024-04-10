package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class n18114_non_solved {
    public static void main(String[] args) throws IOException {

        int sum = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num_size = br.readLine();
        String[] tmp = num_size.split(" ");

        int n = Integer.parseInt(tmp[0]);
        int size = Integer.parseInt(tmp[1]);

        String strNum = br.readLine();
        String[] strArr = strNum.split(" ");

        int[] numArr = new int[n];
        for(int i = 0; i < numArr.length; i++){
            numArr[i] = Integer.parseInt(strArr[i]);
        }

        Arrays.sort(numArr);

        int low = 0;
        int high = numArr.length - 1;
        int mid;

        while(low <= high){
            mid =  (low + high) / 2;
            
            if(numArr[mid] == size){
                System.out.println("1");
                return;
            } else if (numArr[mid] > size) {
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        low = 0;
        high = numArr.length - 1;
        List<Integer> list = Arrays.stream(numArr).boxed().collect(Collectors.toList());

        while(low < high){
            sum = numArr[low] + numArr[high];
            if(sum == size){
                System.out.println(1);
                return;
            } else if(sum > size){
                high--;
            } else {
                if(list.indexOf(size-sum) > low && list.indexOf(size-sum) < high){
                    System.out.println(1);
                    return;
                }
                low++;
            }
        }
        System.out.println(0);
    }
}
