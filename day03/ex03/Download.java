import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Download {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    Map<Integer, String> files = new HashMap<>();

    Iterator <Map.Entry<Integer,String>> it;

    String key = "";

    Integer countFiles;

    List <Thr> thrList = new ArrayList<>();

    private final int DEFAULT_BUFFER_SIZE = 50;

    public Download() {
        try {
            FileInputStream inputStream = new FileInputStream("files_urls.txt");

            Scanner sc = new Scanner(inputStream);

            String line;

            int key;

            String url;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.equals("")) {
                    continue;
                }
                key = Integer.parseInt(line.substring(0, line.indexOf(' ')));
                url = line.substring(line.indexOf(' ') + 1);
                if (files.containsKey(key)) {
                    System.err.println(ANSI_RED + "Key " + key + " already exists" + ANSI_RESET);
                    continue;
                }
                if (url.startsWith("http://")) {
                    url = url.replace("http://", "https://");
                }
                files.put(key, url);
            }
        } catch (IOException e) {
            System.err.println(ANSI_RED + "Can't open the files_urls.txt" + ANSI_RESET);
            System.exit(1);
        }
        it = files.entrySet().iterator();
        countFiles = files.size();
    }

    public Map.Entry<Integer,String> getCurrentUrl() {

        synchronized (key) {

            Map.Entry <Integer,String> res;

            if (it.hasNext()){
                res = it.next();
                return res;
            }
        }

        return null;
    }

    public void startThreads(int countThread) {
        for (int i = 1; i <= countThread; ++i) {
            Thr thr = new Thr(i);
            thr.start();
            thrList.add(thr);
        }
    }

    public void joinThreads() throws InterruptedException {

        Thr thr;

        for (int i = 0; i < thrList.size(); i++) {
            thr = thrList.get(i);
            thr.join();
        }
    }

    public int getCountFiles() {
        return countFiles;
    }

    public class Thr extends Thread {

        private Integer     threadNumber;

        public Thr(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        private void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
            try (FileOutputStream outputStream = new FileOutputStream(file, false)) {

                int read;

                byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            }
        }

        @Override
        public void run() {
            Map.Entry<Integer,String> file;

            synchronized (this) {

                while ((file = getCurrentUrl()) != null) {
                    try {
                        System.out.println(ANSI_YELLOW + "Thread-" + threadNumber + " start download file number " + file.getKey() + ANSI_RESET);
                        URL url = new URL(file.getValue());
                        URLConnection connection = url.openConnection();
                        connection.setRequestProperty("User-Agent", "NING/1.0");

                        try (InputStream is = new BufferedInputStream(connection.getInputStream())) {
                            File dir = new File("download");
                            if (!dir.exists()){
                                dir.mkdir();
                            }
                            copyInputStreamToFile(is, new File("download/" + file.getValue().substring(file.getValue().lastIndexOf('/'))));
                        }
                        System.out.println(ANSI_GREEN + "Thread-" + threadNumber + " finish download file number " + file.getKey() + ANSI_RESET);
                    } catch (IOException e) {
                        System.out.println(ANSI_RED + "Thread-" + threadNumber + " File number " + ". ERROR. " + ANSI_RESET + e.getMessage() + file.getKey());
                    }
                }
            }
        }
    }
}
