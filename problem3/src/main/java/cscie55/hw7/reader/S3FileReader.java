package cscie55.hw7.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import cscie55.hw7.foodservice.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class S3FileReader {

    Logger logger = LogManager.getLogger(S3FileReader.class.getName());

    public Dish [] read(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        Dish[] dishes = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(fileName);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            dishes = mapper.readValue(reader, Dish[].class);
        } catch (IOException e) {
            logger.warn("Unable to read JSON");
        }
        return dishes;
    }
}
