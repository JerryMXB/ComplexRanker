import it.unimi.dsi.fastutil.io.FastBufferedInputStream;
import it.unimi.dsi.law.warc.io.GZWarcRecord;
import it.unimi.dsi.law.warc.io.WarcRecord;
import it.unimi.dsi.law.warc.io.examples.SequentialWarcRecordRead;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chaoqunhuang on 3/8/18.
 */

public class Main {
    final static int IO_BUFFER_SIZE = 64 * 1024;

    public static void main(String arg[]) throws IOException {
        try {
            final String warcFile = "/Users/chaoqunhuang/Documents/LearningToRank/01";
            final boolean isGZipped = true;
            final WarcRecord record = isGZipped ? new GZWarcRecord() : new WarcRecord();

            final FastBufferedInputStream in = new FastBufferedInputStream(
                    new FileInputStream(new File(warcFile + ".warc" + (isGZipped ? ".gz" : ""))), IO_BUFFER_SIZE);

            for (; ; ) {

                if (record.read(in) == -1) break;
                if (isGZipped) System.out.println("GZip header:\n" + ((GZWarcRecord) record).gzheader);
                System.out.println("WARC header:\n" + record.header);
                System.out.println("First ten bytes of block:");

                int n = 10, r;
                while ((r = record.block.read()) != -1 && n-- > 0)
                    System.out.print(Integer.toHexString(r) + " ");

                System.out.println("\n");

            }
        } catch (WarcRecord.FormatException e) {
            e.printStackTrace();
        }


    }
}
