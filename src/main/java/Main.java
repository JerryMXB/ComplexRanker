import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.jwat.common.HeaderLine;
import org.jwat.warc.WarcReader;
import org.jwat.warc.WarcReaderFactory;
import org.jwat.warc.WarcRecord;

/**
 * Created by chaoqunhuang on 3/12/18.
 */
public class Main {
    static String warcFile = "/Users/chaoqunhuang/Documents/LearningToRank/01.warc";

    public static void main(String arg[]) {
        File file = new File(warcFile);
        try {
            InputStream in = new FileInputStream(file);

            int records = 0;
            int errors = 0;

            org.jwat.warc.WarcReader reader = WarcReaderFactory.getReader(in);
            WarcRecord record;

            while ((record = reader.getNextRecord()) != null) {
                printRecord(record);
                System.out.println(IOUtils.toString(record.getPayload().getInputStream()));
                ++records;
                break; // test one doc
            }

            System.out.println("--------------");
            System.out.println("       Records: " + records);
            System.out.println("        Errors: " + errors);
            reader.close();
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void printRecord(WarcRecord record) {
        System.out.println("--------------");
        for (HeaderLine hl : record.getHeaderList()) {
            System.out.println(hl.name + ":" + hl.value);
        }
    }
}
