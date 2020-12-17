import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Test class for Configurator class and methods
 *
 * @see Configurator
 * @author Valentin Hulot
 */
class ConfiguratorTest {

    /** Logger to print test steps in console */
    public static Logger log ;
    /** The configurator to test */
    private static Configurator configurator;

    /**
     * Setup method before all tests
     */
    @BeforeAll
    public static void setup(){
        log = Logger.getLogger(ConfiguratorTest.class.getName());
        log.info("@BeforeAll");
        //Configurator
        configurator = new ConfiguratorImpl();
    }

    /**
     * Check getCategories() method <br>
     * Assert true if all the basics categories are contained
     */
    @DisplayName("Get Categories")
    @Test
    void getCategoriesTest(){
        log.info("Get Categories");
        Category engineCategory = new CategoryImpl("engine");
        Category transmissionCategory = new CategoryImpl("transmission");
        Category exteriorCategory = new CategoryImpl("exterior");
        Category interiorCategory = new CategoryImpl("interior");

        assertTrue(configurator.getCategories().containsAll(Set.of(engineCategory,transmissionCategory,exteriorCategory,interiorCategory)));
    }

    /**
     * Check getVariants() method<br>
     * Assert true if it returns all the variant for a category
     */
    @DisplayName("Get Variants")
    @Test
    void getVariantsTest(){
        log.info("Get Variants");

        Category exteriorCategory = new CategoryImpl("exterior");
        PartType p1 = new PartTypeImpl("XC",part.Exterior.XC.class,exteriorCategory);
        PartType p2 = new PartTypeImpl("XM",part.Exterior.XM.class,exteriorCategory);
        PartType p3 = new PartTypeImpl("XS",part.Exterior.XS.class,exteriorCategory);
        Set<PartType> testSet = Set.of(p1,p2,p3);

        Set<PartType> parts = configurator.getVariants(exteriorCategory);

        assertTrue(parts.containsAll(testSet));
    }


    /**
     * Check getConfiguration() method<br>
     * Assert that the returned object is not null
     */
    @DisplayName("Get Configuration")
    @Test
    void getConfigurationTest(){
        log.info("Get Configuration");

        Configuration conf = configurator.getConfiguration();

        assertFalse(Objects.isNull(conf));
    }

    /**
     * Check getCompatibilityChecker() method<br>
     * Assert that the returned object is not null
     */
    @DisplayName("Get Compatibility Checker")
    @Test
    void getCompatibilityCheckerTest(){
        log.info("Get Compatibility Checker");

        CompatibilityChecker cc = configurator.getCompatibilityChecker();

        assertFalse(Objects.isNull(cc));
    }

}


