import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

public class DecemberSecond {
    static int calculatePlanPoints(ToIntFunction<String> parser) throws IOException {
        int points = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input/" + "12_2_1.txt"));
            String line = reader.readLine();

            while (line != null) {
                points += parser.applyAsInt(line);
                line = reader.readLine();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return points;
    }


    static int parseLineAsGestures(String line) {
        String[] params = line.split(" ");
        return calculateAsGuestures(params[0], params[1]);
    }

    static int parseLineAsPlan(String line) {
        String[] params = line.split(" ");
        return calculateAsPlan(params[0], params[1]);
    }

    static private Map<String, Integer> dictionary = new HashMap<>() {{
        put("A", 1);
        put("B", 2);
        put("C", 3);
        put("X", 1);
        put("Y", 2);
        put("Z", 3);
    }};
    static private Map<Integer, Integer> permutations = new HashMap<>() {{
        put(11, 3);
        put(12, 6);
        put(13, 0);
        put(21, 0);
        put(22, 3);
        put(23, 6);
        put(31, 6);
        put(32, 0);
        put(33, 3);
    }};
    static private Map<String, Integer> outcomes = new HashMap<>() {{
        put("X", 0);
        put("Y", 3);
        put("Z", 6);
    }};

    static private int calculateAsGuestures(String leftGuesture, String rightGuesture) {
        final int leftScore = dictionary.get(leftGuesture);
        final int rightScore = dictionary.get(rightGuesture);
        final int permutation = leftScore * 10 + rightScore;
        final int permutationScore = permutations.get(permutation);
        return permutationScore + rightScore;
    }

    static private int calculateAsPlan(String leftGuesture, String rightPlan) {
        final int leftScore = dictionary.get(leftGuesture);
        final int permutationScore = outcomes.get(rightPlan);
        final int rightScore =
                permutations.entrySet().stream().filter(e -> e.getValue() == permutationScore).filter(e -> e.getKey() / 10 == leftScore).map(e -> e.getKey()).map(x -> x % 10).findFirst().get();
        return permutationScore + rightScore;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(calculatePlanPoints(DecemberSecond::parseLineAsPlan));
    }
}
