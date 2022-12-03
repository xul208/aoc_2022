import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.ToIntFunction;

public class DecemberThird {
    static int calculatePoints(ToIntFunction<String> parser) throws IOException {
        int points = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input" + "/" + "12_3_1.txt"));
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

    static int calculateBadgePoints(ToIntFunction<List<String>> parser) throws IOException {
        int points = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input" + "/" + "12_3_1.txt"));
            String line = reader.readLine();
            List<String> groupBuffer = new ArrayList<>();

            while (line != null) {
                groupBuffer.add(line);
                if (groupBuffer.size() == 3)  {
                    points += parser.applyAsInt(groupBuffer);
                    groupBuffer.clear();
                }

                line = reader.readLine();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return points;
    }


    static int findPriorityFromList(List<String> lines) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (String line : lines) {
            Set<Character> seenInLine = new HashSet<>();
            for (int j = 0; j < line.length(); ++j) {
                final Character currentCharacter = line.charAt(j);
                if (seenInLine.contains(currentCharacter)) {
                    continue;
                }
                seenInLine.add(currentCharacter);
                if (frequency.containsKey(currentCharacter)) {
                    final int newFreq = frequency.get(currentCharacter) + 1;
                    if (newFreq == 3) {
                        System.out.println(currentCharacter);
                        return charToPriority(currentCharacter);
                    }
                    frequency.put(currentCharacter, newFreq);
                } else {
                    frequency.put(currentCharacter, 1);
                }
            }
        }
        return 0;
    }

    static int findPriorityFromLine(String line) {
        final int len = line.length();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < len/2; ++i) {
           seen.add(line.charAt(i));
        }
        for (int i = len/2; i < len; ++i) {
            if (seen.contains(line.charAt(i))) {
                return charToPriority(line.charAt(i));
            }
        }
        return 0;
    }

    static int charToPriority(Character ch) {
        if(Character.isUpperCase(ch)) {
            return 27 + Character.getNumericValue(ch) - Character.getNumericValue('A');
        } else {
            return 1 + Character.getNumericValue(ch) - Character.getNumericValue('a');
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(calculatePoints(DecemberThird::findPriorityFromLine));
        System.out.println(calculateBadgePoints(DecemberThird::findPriorityFromList));
    }
}
