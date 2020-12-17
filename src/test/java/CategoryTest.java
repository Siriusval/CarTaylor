import api.Category;
import impl.CategoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CategoryImpl
 *
 * @see CategoryImpl
 * @author  Valentin Hulot
 */
class CategoryTest {

    /**
     * Logger to print in console
     */
    public static Logger log ;


    @BeforeAll
    public static void setup() {
        log = Logger.getLogger(CategoryTest.class.getName());
        log.info("@BeforeAll");
    }

    /**
     * Test if the constructor throws an exeption if a string is empty
     */
    @Test
    void constructorEmptyStringTest() {
        Executable exec = () -> {
             new CategoryImpl("");
        };

        assertThrows(IllegalArgumentException.class,exec);
    }

}
