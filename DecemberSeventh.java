import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DecemberSeventh {

    static final Map<String, Long> folderSizes = new HashMap<>();

    static Stack<String> currentDir = new Stack<String>();

    static void parseTerminalLine(String line) {
        if (line.startsWith("$")) {
            parseCmdLine(line);
        } else if (line.startsWith("dir")) {
            return;
        } else {
            parseFileSize(line);
        }
    }

    static void parseFileSize(String fileLine) {
        String[] tokens = fileLine.split(" ");
        final long fileSize = Long.parseLong(tokens[0]);
        System.out.printf("adding file size %d \n", fileSize);
        for (int i = 1; i <= currentDir.size(); ++i) {
            final String path = String.join("_", currentDir.subList(0, i));
            final long newFolderSize = folderSizes.getOrDefault(path, 0L) + fileSize;
            System.out.printf("------ new folder size %d, putting to folder %s \n", newFolderSize, path);
            folderSizes.put(path, newFolderSize);
        }
        System.out.println(folderSizes);
        System.out.printf("==== root folder size %d \n", folderSizes.get("/"));
    }

    static void parseCmdLine(String cmd) {
        String[] tokens = cmd.split(" ");
        if (tokens[1].equals("cd")) {
            if (tokens[2].equals("..")) {
                currentDir.pop();
            } else {
                currentDir.push(tokens[2]);
            }
        }
    }

    public static long getTotalSize() {
        return folderSizes.values().stream().max(Long::compareTo).orElse(-1L);
    }

    public static long getTarget() {
        long MAX_DISK = 70_000_000;
        long MIN_SPACE = 30_000_000;
        return getTotalSize() - (MAX_DISK - MIN_SPACE);
    }

    public static long findTargetFolder() {
        final long target = getTarget();
        return folderSizes.values().stream().filter(x -> x > target).min(Long::compareTo).orElse(-1L);
    }

    public static void main(String[] args) throws IOException {
        Util.consumeLines(DecemberSeventh::parseTerminalLine, "12_7_1.txt");
        System.out.println(folderSizes);
        final Long totalSize =
                folderSizes.values().stream().filter(size -> size <= 100_000).mapToLong(Long::longValue).sum();

        System.out.println(totalSize);
        System.out.printf("current root size: %d\n", getTotalSize());
        System.out.printf("target cut: %d\n", getTarget());
        System.out.printf("closest folder: %d\n", findTargetFolder());
    }
}
