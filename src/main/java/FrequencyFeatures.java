import it.unimi.di.big.mg4j.index.Index;
import it.unimi.di.big.mg4j.index.IndexIterator;
import it.unimi.di.big.mg4j.index.IndexReader;
import it.unimi.di.big.mg4j.search.DocumentIterator;
import it.unimi.di.big.mg4j.search.score.BM25Scorer;
import it.unimi.di.big.mg4j.search.score.Scorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * This Class is used to extract Frequency related features from CW09B collections
 * Created by chaoqunhuang on 3/20/18.
 */
public class FrequencyFeatures {
    public static void main(String args[]) throws Exception {
        // The baseName to the collection
        String baseName = args[0];

        long document = 0;
        double score = 0;

        Logger LOGGER = LoggerFactory.getLogger(FrequencyFeatures.class);

        Scorer scorer = new BM25Scorer();

        /** First we open our index. The booleans tell that we want random access to
         * the inverted lists, and we are going to use document sizes (for scoring--see below). */
        final Index text = Index.getInstance(baseName + "-text", true, true);
        final IndexReader reader = text.getReader();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(baseName + "-text.terms"), Charset.forName("UTF-8")));

        IndexIterator iterator;
        while ((iterator = reader.nextIterator()) != null) {
            // for each term in the index, extract the term features: termID,docID,term-freq,doc-term-freq,bm25
            iterator.term(br.readLine());
            scorer.wrap(iterator);
            while ((document = iterator.nextDocument()) != DocumentIterator.END_OF_LIST) {
                System.out.printf("%d,%d,%d,%d,%f\n", iterator.termNumber(), document, iterator.frequency(), iterator.count(), scorer.score());
            }
        }
    }
}
