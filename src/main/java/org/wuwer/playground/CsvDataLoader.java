package org.wuwer.playground;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvDataLoader {

    private static final String DELIMITER = ", ";
    private static final DateTimeFormatter validityDateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");

    private final Logger log = Logger.getLogger(CsvDataLoader.class.getName());

    public List<ValidityDTO> loadCsvData(File file) throws FileNotFoundException {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.lines().map(this::parseValidityDTO).collect(Collectors.toList());
        } catch (IOException e) {
            log.log(Level.WARNING, "failed", e);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private ValidityDTO parseValidityDTO(String csv) {
        String trimmedCSV = StringUtils.trim(csv);
        String[] data = trimmedCSV.split(DELIMITER);
        if (data.length != 4) {
            log.warning("Parsing the following entry failed: " + csv);
            return null;
        }

        //cacht illegalArgumentException if you want the execution to ignore invalid data
        DateTime validityStart = validityDateFormat.parseDateTime(data[2]);
        DateTime validityEnd = validityDateFormat.parseDateTime(data[3]);

        //TODO: add a isValid method to the builder if wrong data is not supposed to stop the execution
        return ValidityDTO.builder()
                .withFirstName(data[0])
                .withLastName(data[1])
                .withValidityStart(validityStart)
                .withValidityEnd(validityEnd)
                .build();

    }
}
