package utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CSVProvider {
    private static final Logger LOG = LoggerFactory.getLogger(CSVProvider.class);
    private static Scanner scanner;

    //метод отдает по ключу путь
    public static String[] get(int row, String keyFile){
        LOG.info("Call method CSVProvider.get: row - {}, keyFile from subscriber.properties - {}", row, keyFile);
        String line = "";
        int i = 0;
        String path = PropertyProvider.get(keyFile);
        try(InputStream is = new FileInputStream(path)) {
            scanner = new Scanner(is);
            while (scanner.hasNext()){
                if (i == row){
                    line = scanner.nextLine();
                    break;
                }else line = scanner.nextLine();
                i++;
            }
        } catch (Exception e) {
            LOG.error("Error CSV initialization. Details: {}", e.getMessage());
        }
        return line.split(";");
    }
}
