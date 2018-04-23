package fgutil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Created by chaoqunhuang on 4/23/18.
 */
public class FeatureGenerationUtil {

    public static void main(String args[]) {
        if (args.length != 2 && args[0] != null && args[1] != null) {
            final int numberOfThreads = Integer.valueOf(args[0]);
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

            int numberOfFiles = Integer.parseInt(args[1]);
            String filePrefix = generateFilePrefix(numberOfFiles);

            for (int i = 0; i < numberOfFiles; i++) {
                executor.submit(new DocumentFeatureGeneration(String.valueOf(Integer.valueOf(filePrefix) + i)));
            }
        } else {
            System.out.println("Usage: [number of threads] [number of files]");
        }
    }

    private static String generateFilePrefix(int numberOfFiles) {
        int length = String.valueOf(numberOfFiles).length();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        return sb.toString();
    }
}
