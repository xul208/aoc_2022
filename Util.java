import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class Util {
    static int calculatePointSum(ToIntFunction<String> parser, String path) throws IOException {
        int points = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input" + "/" + path));
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

    static void consumeLines(Consumer<String> parser, String path) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input" + "/" + path));
            String line = reader.readLine();

            while (line != null) {
                parser.accept(line);
                line = reader.readLine();
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    static String encodeAxis(int y, int x) {
        return Integer.toString(y) + "_" + Integer.toString(x);
    }

    static String decodeFromAxis(String token, int offset) {
        final String[] numbers = token.split("_");
        return numbers[offset];
    }

}
