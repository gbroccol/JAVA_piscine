import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    private static TreeMap<String, Integer> _allWords = new TreeMap<>();

    private static int[] _arrayFirstFile;

    private static int[] _arraySecondFile;

    private static int addWords(String path) throws FileNotFoundException {
        File file = new File(path);

        if (!file.isFile() && !file.canRead()) {
            System.err.println(ANSI_RED + "File signatures.txt not exists" + ANSI_RESET);
            return 0;
        }

        Scanner sc = new Scanner(file);

        int len = _allWords.size();

        while (sc.hasNextLine()) {

            String value = sc.next();

            if (len == 0 || _allWords.containsKey(value) == false) {
                _allWords.put(value, len++);
            }
        }

        return _allWords.size();
    }

    private static int[] fillArray(String path) throws FileNotFoundException {

        int len = _allWords.size();

        int[] array = new int[len];

        File file = new File(path);

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {

            String key = sc.next();

            int place = _allWords.get(key);

            array[place] = array[place] + 1;
        }

        return array;
    }

    private static double getSimilarity() {

        int len = _allWords.size();

        int numerator = 0;

        for (int i = 0; i < len; i++) {
            numerator += _arrayFirstFile[i] * _arraySecondFile[i];
        }

        int valueFirst = 0;

        int valueSecond = 0;

        for (int i = 0; i < len; i++) {
            valueFirst += _arrayFirstFile[i] * _arrayFirstFile[i];
            valueSecond += _arraySecondFile[i] * _arraySecondFile[i];
        }

        return (numerator / Math.sqrt(valueFirst) * Math.sqrt(valueSecond));
    }

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length != 2) {
            System.err.println(ANSI_RED + "Wrong number of arguments" + ANSI_RESET);
            return;
        }

        String pathFirst = args[0];

        String pathSecond = args[1];

        addWords( );
        addWords(pathSecond);
        _arrayFirstFile = fillArray(pathFirst);
        _arraySecondFile = fillArray(pathSecond);
        System.out.println("Similarity = " + (long) (getSimilarity() * 1e2) / 1e2);
    }
}
