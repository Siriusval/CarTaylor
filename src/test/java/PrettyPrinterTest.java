import api.Category;
import api.Configuration;
import api.Configurator;
import api.PartType;
import impl.ConfigurationImpl;
import impl.ConfiguratorImpl;
import impl.PrettyPrinter;
import impl.Visitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrettyPrinterTest {

    /**
     * Logger to print in console
     */
    public static Logger log ;


    @BeforeAll
    public static void setup() {
        log = Logger.getLogger(CategoryTest.class.getName());
        log.info("@BeforeAll");
    }

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



        final String expected  = "Category : transmission\n" +
                "PartType :TM5\n" +
                "Part :part.Transmission\n" +
                "Property :transmission, Value :Optional[Manual]\n" +
                "Property :speed, Value :Optional[5 gears]\n" +
                "Category : interior\n" +
                "PartType :IN\n" +
                "Part :part.Interior\n" +
                "Property :finish, Value :Optional[Standard interior]\n" +
                "Category : exterior\n" +
                "PartType :XC\n" +
                "Part :part.Exterior\n" +
                "Property :paint, Value :Optional[Classic paint]\n" +
                "Category : engine\n" +
                "PartType :EG100\n" +
                "Part :part.Engine\n" +
                "Property :power, Value :Optional[100kW]\n" +
                "Property :type, Value :Optional[Gasoline]\n";
        assertEquals(normalizeLineEnds(expected), normalizeLineEnds(os.toString("UTF-8")));
    }

    /**
     * CRLF, CR -> LF
     * @param s
     * @return
     */
    private static String normalizeLineEnds(String s) {
        return s.replace("\r\n", "\n").replace('\r', '\n');
    }
}
