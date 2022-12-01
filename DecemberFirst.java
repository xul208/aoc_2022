import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

public class DecemberFirst {

    static int findMaxCalories(String fileName) throws IOException {
        int currentMax = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input/" + fileName));
            String line = reader.readLine();
            int currentSum = 0;

            while (line != null) {
                System.out.println(line);
                if (line.isBlank()) {
                   currentMax = Math.max(currentSum, currentMax);
                   currentSum = 0;
                } else {
                    currentSum += Integer.parseInt(line);
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentMax;
    }

    static int findMaxThreeCalories(String fileName) throws IOException {
        SortedSet<Integer> maxThree = new TreeSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input/" + fileName));
            String line = reader.readLine();
            int currentSum = 0;

            while (line != null) {
                System.out.println(line);
                if (line.isBlank()) {
                   maxThree.add(currentSum);
                   if (maxThree.size() > 3) {
                       maxThree.remove(maxThree.first());
                   }
                   currentSum = 0;
                } else {
                    currentSum += Integer.parseInt(line);
                }
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxThree.stream().reduce(Integer::sum).get();
    }

    public static void main(String[] args) throws IOException {
        final int firstResult = DecemberFirst.findMaxCalories("12_1_1.txt");
        System.out.printf("first result is: %d", firstResult);
        final int secondResult = DecemberFirst.findMaxThreeCalories("12_1_1.txt");
        System.out.printf("second result is: %d", secondResult);
    }
}
