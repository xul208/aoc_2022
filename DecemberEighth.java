import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecemberEighth {
    static List<List<Integer>> forest = new ArrayList<>();

    static Set<String> visibleTrees = new HashSet<>();

    public static void parseTreeLine(String treeLine) {
        List<Integer> trees = new ArrayList<>();
        for (int i = 0; i < treeLine.length(); ++i) {
            final char treeChar = treeLine.charAt(i);
            final int treeHeight = Character.getNumericValue(treeChar);
            trees.add(treeHeight);
        }
        forest.add(trees);
    }

    public static void inspectTreesHorizontally() {
        for (int i = 0; i < forest.size(); ++i) {
            List<Integer> row = forest.get(i);
            int currentMax = 0;
            for (int j = 0; j < row.size(); ++j) {
                if (row.get(j) > currentMax || j == 0) {
                    visibleTrees.add(Util.encodeAxis(i, j));
                    currentMax = row.get(j);
                }
            }
            currentMax = 0;
            for (int j = row.size() - 1; j >= 0; --j) {
                if (row.get(j) > currentMax || j == (row.size() - 1)) {
                    visibleTrees.add(Util.encodeAxis(i, j));
                    currentMax = row.get(j);
                }
            }
        }
    }

    public static int calculateScenicScore(int y, int x) {
        final int height = forest.get(y).get(x);
        final int columnNumber = forest.get(0).size();
        int[] scores = {0, 0, 0, 0};
        for (int i = y - 1; i >= 0; --i) {
            final int current = forest.get(i).get(x);
            scores[0]++;
            if (current >= height) break;
        }
        for (int j = x - 1; j >= 0; --j) {
            final int current = forest.get(y).get(j);
            scores[1]++;
            if (current >= height) break;
        }
        for (int i = y + 1; i < forest.size(); ++i) {
            final int current = forest.get(i).get(x);
            scores[2]++;
            if (current >= height) break;
        }

        for (int j = x + 1; j < columnNumber; ++j) {
            final int current = forest.get(y).get(j);
            scores[3]++;
            if (current >= height) break;
        }
        return scores[0] * scores[1] * scores[2] * scores[3];
    }

    public static int findMaxScore() {
        int currentMax = 0;
        final int columnNumber = forest.get(0).size();
        for (int i = 0; i < forest.size(); ++i) {
            for (int j = 0; j < columnNumber; ++j) {
                int currentScore = calculateScenicScore(i, j);
                currentMax = Math.max(currentScore, currentMax);
            }
        }
        return currentMax;
    }

    public static void inspectTreesVertically() {
        final int columnNum = forest.get(0).size();
        for (int j = 0; j < columnNum; ++j) {
            int currentMax = 0;
            for (int i = 0; i < forest.size(); ++i) {
                if (forest.get(i).get(j) > currentMax || i == 0) {
                    visibleTrees.add(Util.encodeAxis(i, j));
                    currentMax = forest.get(i).get(j);
                }
            }
            currentMax = 0;
            for (int i = forest.size() - 1; i >= 0; --i) {
                if (forest.get(i).get(j) > currentMax || i == (forest.size() - 1)) {
                    visibleTrees.add(Util.encodeAxis(i, j));
                    currentMax = forest.get(i).get(j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Util.consumeLines(DecemberEighth::parseTreeLine, "12_8_1.txt");
        System.out.println(forest);
        inspectTreesHorizontally();
        inspectTreesVertically();
        System.out.println(visibleTrees);
        System.out.println(visibleTrees.size());
        System.out.println(calculateScenicScore(3, 2));
        System.out.println(findMaxScore());
    }
}
