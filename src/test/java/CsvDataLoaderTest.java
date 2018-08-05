import org.junit.Test;
import org.wuwer.playground.CsvDataLoader;
import org.wuwer.playground.ValidityDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CsvDataLoaderTest {

    @Test
    public void testBasicCSVLoading() throws FileNotFoundException {
        //given
        File testFile = getFileFromResources("data.csv");
        CsvDataLoader csvDataLoader = new CsvDataLoader();

        //when
        List<ValidityDTO> validityDTOS = csvDataLoader.loadCsvData(testFile);

        //then
        assertTrue(validityDTOS.size() == 2);
    }

    private File getFileFromResources(String path) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String usableFilePath = classLoader.getResource(path).getFile();
        return new File(usableFilePath);
    }

}
