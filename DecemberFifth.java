import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DecemberFifth {

    static List<List<Character>> cargos = new ArrayList<>();

    static Map<Integer, Integer> offsetMap = new HashMap<>();

    static void parseOffsetMap(String cargoLine) {
        if (cargoLine.startsWith("[")) {
            return;
        }
        for (int i = 0; i < cargoLine.length(); ++i) {
            if (Character.isDigit(cargoLine.charAt(i))) {
                offsetMap.put(i, Integer.parseInt(String.valueOf(cargoLine.charAt(i))));
            }
        }
    }

    static void parseCargoLine(String cargoLine) {
        for (int i = 0; i < cargoLine.length(); ++i) {
            Character current = cargoLine.charAt(i);
            if (Character.isAlphabetic(current)) {
                int targetPile = offsetMap.get(i);
                if (cargos.size() <= targetPile) {
                    final int initialSize = cargos.size();
                    for (int j = 0; j <= (targetPile - initialSize); ++j) {
                        final List<Character> pile = new ArrayList<>();
                        cargos.add(pile);
                    }
                }
                cargos.get(targetPile).add(current);
            }
        }
    }

    static void parseMove(String moveLine) {
        moveLine = moveLine.replaceAll("move|from|to", "");
        List<Integer> tokens = Arrays.stream(moveLine.split(" ")).filter(s -> {
            return !s.isBlank();
        }).map(Integer::parseInt).toList();
        final int moveCount = tokens.get(0);
        final int fromPile = tokens.get(1);
        final int toPile = tokens.get(2);
        for (int i = 0; i < moveCount; ++i) {
            Character currentChar = cargos.get(fromPile).remove(0);
            cargos.get(toPile).add(0, currentChar);
        }
    }

     static void parseMove2(String moveLine) {
        moveLine = moveLine.replaceAll("move|from|to", "");
        List<Integer> tokens = Arrays.stream(moveLine.split(" ")).filter(s -> {
            return !s.isBlank();
        }).map(Integer::parseInt).toList();
        final int moveCount = tokens.get(0);
        final int fromPile = tokens.get(1);
        final int toPile = tokens.get(2);
        for (int i = moveCount-1; i >= 0; --i) {
            Character currentChar = cargos.get(fromPile).remove(i);
            cargos.get(toPile).add(0, currentChar);
        }
    }


    public static void main(String[] args) throws IOException {
        Util.consumeLines(DecemberFifth::parseOffsetMap, "12_5_0.txt");
        Util.consumeLines(DecemberFifth::parseCargoLine, "12_5_0.txt");
        Util.consumeLines(DecemberFifth::parseMove2, "12_5_1.txt");
        System.out.println(cargos);

    }
}
