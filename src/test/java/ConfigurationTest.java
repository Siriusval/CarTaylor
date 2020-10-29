import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class ConfigurationTest {

    private static Logger log = Logger.getLogger(ConfigurationTest.class.getName());
    private static ConfiguratorImpl configurator;
    private static Configuration configuration;
    @BeforeAll
    public static void setup(){
        log.info("@BeforeAll");

        configurator = new ConfiguratorImpl();
        configuration = configurator.getConfiguration();
    }

    @AfterEach
    public void tearDown() {
        log.info("@AfterEach");
        configuration.clear();
    }


    @DisplayName("is Complete fail")
    @Test
    public void isCompleteFailTest() {

        Category lastCategory = null;
        //Add the first part of each cat
        for(Category cat : configurator.getCategories()){
            Set<PartType> parts = configurator.getVariants(cat);
            parts.iterator().next();
            for(PartType part : configurator.getVariants(cat)){
                configuration.selectPart(part);
                break;
            }
            lastCategory = cat;
        }

        //unselect a part so configuration is not complete
        configuration.unselectPartType(lastCategory);

        assertFalse(configuration.isComplete());
    }

    @DisplayName("is Complete Success")
    @Test
    public void isCompleteSuccessTest() {

        //Add the first part of each cat
        for(Category cat : configurator.getCategories()){
            Set<PartType> parts = configurator.getVariants(cat);
            parts.iterator().next();
            for(PartType part : configurator.getVariants(cat)){
                configuration.selectPart(part);
                break;
            }
        }

        assertTrue(configuration.isComplete());
    }

    @DisplayName("is Valid")
    @Test
    public void isValidTest() {
        List<String> partsToChoose= Arrays.asList("EG100","TM5","XC","IN");

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        assertTrue(configuration.isValid());
    }

    @DisplayName("is Valid Incompatibility")
    @Test
    public void isValidIncompatibilityTest() {
        List<String> partsToChoose= Arrays.asList("EG100","TA5","XC","IN"); //EG100 and TA5 incompatibles

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        assertFalse(configuration.isValid());
    }

    @DisplayName("is Valid Requirement OK")
    @Test
    public void isValidRequirementOKTest() {
        List<String> partsToChoose= Arrays.asList("EH120","TC120","XC","IN"); //EH120 require TC120

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        boolean isValid = configuration.isValid();
        assertTrue(isValid);
    }

    @DisplayName("is Valid Requirement Missing")
    @Test
    public void isValidRequirementMissingTest() {
        List<String> partsToChoose= Arrays.asList("EH120","TM5","XC","IN"); //EH120 require TC120

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        assertFalse(configuration.isValid());
    }
}
