import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ConfiguratorTest {

    private static Logger log = Logger.getLogger(ConfiguratorTest.class.getName());
    private static ConfigurationImpl configuration;
    private static CompatibilityChecker compatibilityChecker;

    @BeforeAll
    public static void setup(){
        log.info("@BeforeAll");
        //Configuration
        Set<PartType> selectedParts = new HashSet<>();
        configuration = new ConfigurationImpl(selectedParts);

        //Configurator
        compatibilityChecker = new CompatibilityCheckerImpl();
    }

    @DisplayName("Get Categories")
    @Test
    public void getCategoriesTest(){
        log.info("Get Categories");
        Category c1 = new CategoryImpl("engine");
        Set<Category> categories = new HashSet<>();
        categories.add(c1);

        Set<PartType> variants = new HashSet<>();

        Configurator configurator = new ConfiguratorImpl(configuration, compatibilityChecker, categories, variants);
        assertTrue(configurator.getCategories().contains(c1));
    }

    @DisplayName("Get Variants")
    @Test
    public void getVariantsTest(){
        log.info("Get Variants");

        Category c1 = new CategoryImpl("engine");
        Set<Category> categories = new HashSet<>();
        categories.add(c1);

        Set<PartType> variants = new HashSet<>();
        PartType v1 = new PartTypeImpl("EG100",c1);
        variants.add(v1);

        Configurator configurator = new ConfiguratorImpl(configuration, compatibilityChecker, categories, variants);
        assertTrue(configurator.getVariants(c1).contains(v1));
    }

    @DisplayName("Get Configuration")
    @Test
    public void getConfigurationTest(){
        log.info("Get Configuration");

        Set<Category> categories = new HashSet<>();
        Set<PartType> variants = new HashSet<>();

        Configurator configurator = new ConfiguratorImpl(configuration, compatibilityChecker, categories, variants);

        assertTrue(configurator.getConfiguration().equals(configuration));
    }

    @DisplayName("Get Compatibility Checker")
    @Test
    public void getCompatibilityCheckerTest(){
        log.info("Get Compatibility Checker");

        Set<Category> categories = new HashSet<>();
        Set<PartType> variants = new HashSet<>();

        Configurator configurator = new ConfiguratorImpl(configuration, compatibilityChecker, categories, variants);

        assertTrue(configurator.getCompatibilityChecker().equals(compatibilityChecker));
    }


}


