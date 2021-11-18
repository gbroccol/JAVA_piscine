import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Program {

    private static final int BYTES_SIZE = 8;

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    private static String _rightSignature;

    private static HashMap<String, String> parseSignatures(File fileInput) throws IOException {
        HashMap<String, String> allSignatures = new HashMap<>();

        Scanner sc = new Scanner(fileInput);

        while (sc.hasNextLine()) {

            String value = sc.next();

            if (value.lastIndexOf(",") != value.length() - 1) {
                return null;
            } else {
                value = value.substring(0, value.length() - 1);
            }

            String key = sc.nextLine();

            key = key.replaceAll("\\s", "");
            allSignatures.put(key, value);
        }

        return (allSignatures);
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {

            int v = bytes[j] & 0xFF;

            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }

        return new String(hexChars);
    }

    private static boolean isFind(HashMap<String, String> allSign, String sign) {
        for (String key : allSign.keySet()) {
            if (sign.indexOf(key) == 0) {
                _rightSignature = allSign.get(key);
                return true;
            }
        }

        return false;
    }

    private static void checkFile(String path, HashMap<String, String> allSign) throws IOException {
        File checkSignatureFile = new File(path);

        if (!checkSignatureFile.isFile() || !checkSignatureFile.canRead() || checkSignatureFile.isDirectory() || !checkSignatureFile.exists()) {
            System.err.println(ANSI_RED + "File not exists" + ANSI_RESET);
            return;
        }

        FileInputStream checkSignature = new FileInputStream(checkSignatureFile);

        byte[] sign = new byte[BYTES_SIZE];

        checkSignature.read(sign, 0, BYTES_SIZE);

        String hexSignature = bytesToHex(sign);

        if (isFind(allSign, hexSignature)) {

            FileWriter out = new FileWriter("result.txt", true);

            out.append(_rightSignature);
            out.append('\n');
            out.close();
            System.out.println("PROCESSED");
        } else {
            System.out.println("UNDEFINED");
        }
        checkSignature.close();
    }

    public static void main(String[] args) throws IOException {
        File allSignaturesFile = new File("signatures.txt");

        if (!allSignaturesFile.isFile() || !allSignaturesFile.canRead() || !allSignaturesFile.exists() || allSignaturesFile.isDirectory()) {
            System.err.println(ANSI_RED + "File signatures.txt not exists" + ANSI_RESET);
            return;
        }

        HashMap<String, String> signatures = parseSignatures(allSignaturesFile);

        Scanner sc = new Scanner(System.in);

        String path = sc.nextLine();

        if (path.equals("42") == false) {
            FileWriter out = new FileWriter("result.txt", false);

            out.close();
        }

        while (path.equals("42") == false) {
            _rightSignature = "";
            checkFile(path, signatures);
            path = sc.nextLine();
        }
    }
}
