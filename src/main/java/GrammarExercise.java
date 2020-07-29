import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstWordList = scanner.nextLine();
        String secondWordList = scanner.nextLine();
        try{
            List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
            System.out.println(result.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        String[] firstString = firstWordList.split(",");
        String[] secondString = secondWordList.split(",");

        Stream<String> opstream = Stream.concat(Arrays.stream(firstString), Arrays.stream(secondString));
        long cnt = opstream.filter(str -> "".equals(str) || !str.matches("[a-zA-Z]+")).count();
        if (cnt != 0) {
            throw new RuntimeException("input not valid");
        }
        Stream<String> firstStream = Arrays.stream(firstString);
        List<String> secondUpperList = Arrays.stream(secondString).map(str2 -> str2.toUpperCase()).collect(Collectors.toList());
        List<String> ans = firstStream.distinct()
                .map(str -> str.toUpperCase())
                .filter(str -> secondUpperList.contains(str))
                .sorted().map(str -> String.join(" ", str.split("")))
                .collect(Collectors.toList());

        return ans;
    }
}
