import api.Category;
import api.CompatibilityManager;
import api.PartType;
import impl.CategoryImpl;
import impl.CompatibilityManagerImpl;
import impl.PartTypeImpl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Test class for CompatibilityManagerImpl
 *
 * @see CompatibilityManagerImpl
 * @author  Valentin Hulot
 */
class CompatibilityManagerClass {

    public static Logger log = Logger.getLogger(CompatibilityManagerClass.class.getName());
    public static CompatibilityManager cm;

    @BeforeAll
    public static void setup(

    ) {
        log.info("@BeforeAll - executes once before all test methods in this class");
        cm = new CompatibilityManagerImpl();
    }


    @DisplayName("Add Incompatibility Test")
    @Test
    public void addIncompatibility() throws Exception {
        log.info("Add Incompatibility Test");

        //Create EG100
        Category engineCategory = new CategoryImpl("Engine");
        PartType EG100 = new PartTypeImpl("EG100", engineCategory);

        //Create TA5
        Category transmissionCategory = new CategoryImpl("Transmission");
        PartType TA5 = new PartTypeImpl("TA5", transmissionCategory);

        //Create set
        Set<PartType> incompatibilities = new HashSet<>();
        incompatibilities.add(TA5);

        //Add incompatibility
        cm.addIncompatibilities(EG100,incompatibilities);

        //Check if contained
        assertTrue(cm.getIncompatibilities(EG100).contains(TA5));

    }


    //TEMPLATE FUNCTIONS UNDER


    @BeforeEach
    public void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }

    @DisplayName("Single test successful")
    @Test
    public void testSingleSuccessTest() {
        log.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    public void testShowSomething() {
    }

    @AfterEach
    public void tearDown() {
        log.info("@AfterEach - executed after each test method.");
    }

    @AfterAll
    public static void done() {
        log.info("@AfterAll - executed after all test methods.");
    }


}