import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class ConfiguratorTest {

    private static Logger log = Logger.getLogger(ConfiguratorTest.class.getName());
    private static Configurator configurator;

    @BeforeAll
    public static void setup(){
        log.info("@BeforeAll");
        //Configurator
        configurator = new ConfiguratorImpl();
    }

    @DisplayName("Get Categories")
    @Test
    public void getCategoriesTest(){
        log.info("Get Categories");
        Category engineCategory = new CategoryImpl("engine");
        Category transmissionCategory = new CategoryImpl("transmission");
        Category exteriorCategory = new CategoryImpl("exterior");
        Category interiorCategory = new CategoryImpl("interior");

        assertTrue(configurator.getCategories().containsAll(Set.of(engineCategory,transmissionCategory,exteriorCategory,interiorCategory)));
    }


    @DisplayName("Get Variants")
    @Test
    public void getVariantsTest(){
        log.info("Get Variants");

        Category exteriorCategory = new CategoryImpl("exterior");
        PartType p1 = new PartTypeImpl("XC",exteriorCategory);
        PartType p2 = new PartTypeImpl("XM",exteriorCategory);
        PartType p3 = new PartTypeImpl("XS",exteriorCategory);
        Set<PartType> testSet = Set.of(p1,p2,p3);

        Set<PartType> parts = configurator.getVariants(exteriorCategory);

        assertTrue(parts.containsAll(testSet));
    }


    @DisplayName("Get Configuration")
    @Test
    public void getConfigurationTest(){
        log.info("Get Configuration");

        Configuration conf = configurator.getConfiguration();

        assertTrue(!Objects.isNull(conf) && conf instanceof Configuration);
    }


    @DisplayName("Get Compatibility Checker")
    @Test
    public void getCompatibilityCheckerTest(){
        log.info("Get Compatibility Checker");

        CompatibilityChecker cc = configurator.getCompatibilityChecker();

        assertTrue(!Objects.isNull(cc) && cc instanceof CompatibilityChecker);
    }

}


