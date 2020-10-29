import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.logging.Logger;

/**
 * Test Class for Configuration Methods
 */
public class ConfigurationTest {
    /** Logger to print in console */
    private static Logger log ;
    /** Configurator ref to be used in the tests */
    private static ConfiguratorImpl configurator;

    /** Shortcut to configurator.getConfiguration()*/
    private static Configuration configuration;


    @BeforeAll
    public static void setup(){
        log = Logger.getLogger(ConfigurationTest.class.getName());
        log.info("@BeforeAll");

        configurator = new ConfiguratorImpl();
        configuration = configurator.getConfiguration();
    }

    /**
     * Reset configuration after each test
     */
    @AfterEach
    public void tearDown() {
        log.info("@AfterEach");
        configuration.clear();
    }

    /**
     * Test a configuration which is not complete<br>
     * Fill configuration, remove the last part and assert that the config is incomplete
     */
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

    /**
     * Test a configuration which is complete<br>
     * Add a part for each category, assert that the config is complete
     */
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

    /**
     * Test a valid configuration (complete + incompatibilities and requirements ok)<br>
     * Assert that the configuration is valid
     */
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

    /**
     * Test a not valid configuration with incompatibility constraint<br>
     * complete + incompatibilities not ok<br>
     * Add a config with an incompatibility, Assert that the configuration is not valid
     */
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

    /**
     * Test a valid configuration with requirements ok<br>
     * Add a valid requirement to the configuration, assert that it is valid
     */
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

    /**
     * Test a valid configuration with requirements missing<br>
     * Add a part that need another, don't supply the other<br>
     * Assert that the config is not valid
     */
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

    /**
     * Test the selectPart and getSelectedParts functions<br>
     * Add parts, store them in a set<br>
     * Assert that the function return a set that contains all the added parts
     */
    @DisplayName("Get Selected Parts")
    @Test
    public void getSelectedPartsTest(){
        List<String> partsToChoose= Arrays.asList("EH120","TC120","XC","IN"); //EH120 require TC120
        Set<PartType> selectedParts = new HashSet<>();

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    selectedParts.add(part);
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        assertTrue(configuration.getSelectedParts().containsAll(selectedParts));
    }

    /**
     * Test the getSelectionForCategory method<br>
     * Add a part for a specific category "c" <br>
     * Check if that part is returned when getSelectionForCategory(c) is called
     */
    @DisplayName("Get Selection for Category")
    @Test
    public void getSelectionForCategoryTest(){
        List<String> partsToChoose= Arrays.asList("EH120","TC120","XC","IN"); //EH120 require TC120
        Category selectedCategory = null;
        PartType selectedPartForEngine = null;


        for(Category cat : configurator.getCategories()){

            for(PartType part : configurator.getVariants(cat)){
                if (partsToChoose.contains(part.getName())){
                    if(cat.getName().equals("engine")){
                        selectedCategory = cat;
                        selectedPartForEngine = part;
                    }
                    configuration.selectPart(part);
                    break;
                }
            }
        }

        assertEquals(selectedPartForEngine,configuration.getSelectionForCategory(selectedCategory));
    }

}
