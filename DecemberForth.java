import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DecemberForth {

    static int countOverlap(String line) {
        String[] assignments = line.split("[,-]");
        List<Integer> assignedNum = Arrays.stream(assignments).map(Integer::parseInt).toList();
        if ((assignedNum.get(0) - assignedNum.get(2)) * (assignedNum.get(1) - assignedNum.get(3)) <= 0) {
            return 1;
        }
        return 0;
    }

    static int countTouch(String line) {
        String[] assignments = line.split("[,-]");
        List<Integer> assignedNum = Arrays.stream(assignments).map(Integer::parseInt).toList();
        if (assignedNum.get(2) > assignedNum.get(1) || assignedNum.get(3) < assignedNum.get(0)) {
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Util.calculatePointSum(DecemberForth::countOverlap, "12_4_1.txt"));
        System.out.println(Util.calculatePointSum(DecemberForth::countTouch, "12_4_1.txt"));
    }
}
