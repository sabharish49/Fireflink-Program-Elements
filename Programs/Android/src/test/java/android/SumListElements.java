package android;
import java.util.ArrayList;
import java.util.List;

public class SumListElements {
    public static void main(String[] args) {
       
        List<Integer> integerList = new ArrayList<>();
        
        // Adding elements to the list
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        
        
        int sum = 0;
        for (int num : integerList) {
            sum += num;
        }
     
        System.out.println("The sum of the list elements is: " + sum);
    }
   
    
}
