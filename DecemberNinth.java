import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DecemberNinth {

    static Util.CustomPair<Integer> headPos = new Util.CustomPair<>(0, 0);
    static Util.CustomPair<Integer> tailPos = new Util.CustomPair<>(0, 0);
    static Set<Util.CustomPair> tailPosHistory = new HashSet<>();

    static void parseLine(String line) {
        final String[] tokens = line.split(" ");
        Util.CustomPair<Integer> vector = null;
        final int repeat = Integer.parseInt(tokens[1]);
        switch (tokens[0]) {
            case "U" -> vector = new Util.CustomPair<>(1, 0);
            case "D" -> vector = new Util.CustomPair<>(-1, 0);
            case "L" -> vector = new Util.CustomPair<>(0, -1);
            case "R" -> vector = new Util.CustomPair<>(0, 1);
            default -> {
            }
        }
        simulate(vector, repeat);
    }

    static void simulate(Util.CustomPair<Integer> vector, int repeat) {
        for (int i = 0; i < repeat; ++i) {
            int prevY = headPos.getKey() + vector.getKey();
            int prevX = headPos.getValue() + vector.getValue();
            headPos.setKey(prevY);
            headPos.setValue(prevX);
            trackNewHeadPosition();
        }
        System.out.println("==================");
    }

    static void trackNewHeadPosition() {
        tailPosHistory.add(tailPos);
        System.out.printf("head moved to %d - %d \n", headPos.getKey(), headPos.getValue());
        int distanceY = headPos.getKey() - tailPos.getKey();
        int distanceX = headPos.getValue() - tailPos.getValue();
        var distance = Math.max(Math.abs(distanceX), Math.abs(distanceY));
        if (distance > 1) {
            int moveY = Integer.signum(distanceY);
            int moveX = Integer.signum(distanceX);
            int newY = tailPos.getKey() + moveY;
            int newX = tailPos.getValue() + moveX;
            tailPos.setKey(newY);
            tailPos.setValue(newX);
            System.out.printf("tail moving to %d - %d \n", newY, newX);
            tailPosHistory.add(tailPos);
        }
    }

    public static void main(String[] args) throws IOException {
        Util.consumeLines(DecemberNinth::parseLine, "12_9_1.txt");
        System.out.println(tailPosHistory.size());
    }
}
