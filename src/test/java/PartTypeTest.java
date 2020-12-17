
import api.PartType;
import impl.CategoryImpl;
import impl.PartTypeImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for PartType
 *
 * @see PartTypeImpl
 * @author  Valentin Hulot
 */
class PartTypeTest {

    /** Logger to print details in console*/
    public static Logger log ;

    /**
     * Setup before all tests
     */
    @BeforeAll
    public static void setup() {
        log = Logger.getLogger(PartTypeTest.class.getName());
        log.info("@BeforeAll");
    }

    /**
     * Test the constructor when the name of the part is empty<br>
     * Should throw an exception
     */
    @Test
    void constructorEmptyStringTest() {
        Executable exec = () -> {
            new PartTypeImpl("",part.Engine.class,new CategoryImpl("engine"));
        };

        assertThrows(IllegalArgumentException.class,exec);
    }

}
