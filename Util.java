import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
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

    public static class CustomPair<T> {
        private T key;

        public CustomPair(T key, T value) {
            this.key = key;
            this.value = value;
        }

        private T value;

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CustomPair that = (CustomPair) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
