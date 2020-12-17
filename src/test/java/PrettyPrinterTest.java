import api.Category;
import api.Configuration;
import api.Configurator;
import api.PartType;
import impl.ConfiguratorImpl;
import impl.PrettyPrinter;
import api.Visitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class test for PrettyPrinter (print details of the configuration)
 */
class PrettyPrinterTest {

    /**
     * Logger to print in console
     */
    public static Logger log ;

    /**
     * Setup before all tests
     */
    @BeforeAll
    public static void setup() {
        log = Logger.getLogger(CategoryTest.class.getName());
        log.info("@BeforeAll");
    }

    /**
     * Test the prettyPrinter on a test configuration
     * @throws UnsupportedEncodingException _
     */
    @Test
    void testPrettyPrinter() throws UnsupportedEncodingException {

        Configurator configurator = new ConfiguratorImpl();
        Configuration configuration = configurator.getConfiguration();


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Visitor visitor = new PrettyPrinter(new PrintStream(os));

        //Add components
        List<String> partsToChoose= Arrays.asList("EG100","TM5","XC","IN");

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        configuration.accept(visitor);



        final String expectedCategory  = "Category : engine\n" +
                "PartType :EG100\n" +
                "Part :part.Engine$EG100\n" +
                "Property :power, Value :100kW\n" +
                "Property :type, Value :GASOLINE\n";
        final String expectedExterior  ="Category : exterior\n" +
                "PartType :XC\n" +
                "Part :part.Exterior$XC\n" +
                "Property :paint, Value :CLASSIC\n";
        final String expectedTransmission  = "Category : transmission\n" +
                "PartType :TM5\n" +
                "Part :part.Transmission$TM5\n" +
                "Property :type, Value :MANUAL\n" +
                "Property :speed, Value :GEAR_5\n";
        final String expectedInterior  =  "Category : interior\n" +
                "PartType :IN\n" +
                "Part :part.Interior$IN\n" +
                "Property :style, Value :STANDARD\n";

        assertTrue(normalizeLineEnds(os.toString("UTF-8")).contains(expectedCategory));
        assertTrue(normalizeLineEnds(os.toString("UTF-8")).contains(expectedExterior));
        assertTrue(normalizeLineEnds(os.toString("UTF-8")).contains(expectedTransmission));
        assertTrue(normalizeLineEnds(os.toString("UTF-8")).contains(expectedInterior));
    }

    /**
     * Normalize the a unique type of end line<br>
     * CRLF, CR -> LF
     * @param s, the string to normalize
     * @return the string with all the same endlines
     */
    private static String normalizeLineEnds(String s) {
        return s.replace("\r\n", "\n").replace('\r', '\n');
    }
}
