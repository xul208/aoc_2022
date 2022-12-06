import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DecemberSixth {

    public static int findPacketHead(String packet) {
        final int bufferSize = 14;
        List<Character> buffer = new ArrayList<>();
        for(int i = 0; i < packet.length(); ++i) {
            Character currentChar = packet.charAt(i);
            buffer.add(currentChar);
            if (buffer.size() > bufferSize) {
                buffer.remove(0);
            }
            if (buffer.size() == bufferSize && buffer.stream().distinct().count()==bufferSize) {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Util.calculatePointSum(DecemberSixth::findPacketHead, "12_6_0.txt"));
        System.out.println(Util.calculatePointSum(DecemberSixth::findPacketHead, "12_6_1.txt"));
    }
}
