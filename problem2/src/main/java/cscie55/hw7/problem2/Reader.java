package cscie55.hw7.problem2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

public class Reader {

    private Library library;
    private Logger logger = Logger.getLogger(Reader.class.getName());

    public Reader() {
        this.library = Library.getInstance();
    }

    public void read(String uri) throws IOException {
        File file = new File(uri);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                parseAndPopulate(line);
            }
        } catch (IOException e) {
           logger.warning("Unable to read file " + e.getCause());
        } finally{
            bufferedReader.close();
        }
    }

    private Collection<Checkout> parseAndPopulate(String line) throws IOException {
        CSVParser parser = CSVParser.parse(line,
                CSVFormat.RFC4180.withHeader("name", "type", "desc", "yyyy",
                        "MM", "dd", "title", "author", "summary", "short", "year"));
        CSVRecord record = parser.getRecords().get(0);
        Checkout checkout = new Checkout(record.get("title"),
                record.get("author"), Integer.parseInt(record.get("yyyy")));

        Collections.sort(library.getCheckouts());
        return this.library.addCheckout(checkout);
    }
}
