package fgutil;

import java.io.IOException;

/**
 * Created by chaoqunhuang on 4/23/18.
 */
public class DocumentFeatureGeneration implements Runnable {
    private final String fileNamePrefix;

    public DocumentFeatureGeneration(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting extracting document features for file x" + fileNamePrefix);
            String cmd[] = {
                    "../../../../external/local/bin/fgtrain",
                    "gov2-all-kstem.qry",
                    "split/x" + fileNamePrefix,
                    "gov2_indri",
                    "> split/gov2_temp" + fileNamePrefix + ".csv"
            };
            Process p = Runtime.getRuntime().exec(cmd);
            int exit = p.waitFor();
            System.out.println((exit == 0 ? "Extract document features success for file" : "Extract document features failed for file " + exit) + fileNamePrefix);
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
