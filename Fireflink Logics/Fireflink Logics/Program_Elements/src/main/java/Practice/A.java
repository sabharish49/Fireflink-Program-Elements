package Practice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) {
        String insureType = "SELF:28, SPOUSE:30, SON:13|2, DAUGHTER:14|2 ";
        
        List<String> relationshipList = new ArrayList<>();
        List<Integer> ageList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("(\\w+):(\\d+)(?:\\|(\\d+))?,?\\s*");
        Matcher matcher = pattern.matcher(insureType);
        
        while (matcher.find()) {
            String relationship = matcher.group(1);
            int age = Integer.parseInt(matcher.group(2));
            int count = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 1;
            
            relationshipList.add(relationship);
            ageList.add(age);
            countList.add(count);
        }
        
        System.out.println("Relationships: " + relationshipList);
        System.out.println("Ages: " + ageList);
        System.out.println("Counts: " + countList);
    }
}
