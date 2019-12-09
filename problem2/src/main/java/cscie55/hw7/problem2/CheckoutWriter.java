package cscie55.hw7.problem2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CheckoutWriter {

    private Logger logger = Logger.getLogger(CheckoutWriter.class.getName());

    public void write(String pathAndFileName, List<Checkout> checkouts){
        try(FileWriter fileWriter = new FileWriter(pathAndFileName)){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            List<String> lines = checkouts.stream()
                    .map(checkout -> checkout.getTitle() + "," + checkout.getAuthor() + "," +
                            checkout.getPublicationDate())
                    .collect(Collectors.toList());
            lines.stream()
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line);
                        } catch (IOException e) {
                            logger.warning("Unable to write to file " + e.getCause());
                        }
                    });
        } catch (IOException e) {
            logger.warning("Unable to write to file " + e.getCause());
        }
    }
}
