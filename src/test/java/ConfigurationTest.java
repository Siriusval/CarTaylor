import api.*;
import impl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ConfigurationTest {

    private static Logger log = Logger.getLogger(ConfigurationTest.class.getName());
    private static ConfiguratorImpl configurator;
    private static Set<Category> categories;
    private static Set<PartType> variants;

    @BeforeAll
    public static void setup(){
        log.info("@BeforeAll");

        //Compatibility Checker
        compatibilityChecker = new CompatibilityCheckerImpl();

        categories = new HashSet<>();
        Category engineCategory = new CategoryImpl("engine");
        Category transmissionCategory = new CategoryImpl("transmission");
        //Category exteriorCategory = new CategoryImpl("exterior");
        //Category interiorCategory = new CategoryImpl("interior");
        categories.add(engineCategory);
        categories.add(transmissionCategory);
        //categories.add(exteriorCategory);
        //categories.add(interiorCategory);

        variants = new HashSet<>();
        PartType enginePart1 = new PartTypeImpl("EG100",engineCategory);
        PartType transmissionPart = new PartTypeImpl("TM5",transmissionCategory);
        variants.add(enginePart1);
        variants.add(transmissionPart);

        //Inc with EG100
        PartType incompatiblePart = new PartTypeImpl("TA5",transmissionCategory);
        variants.add(incompatiblePart);

        //Required
        PartType requiredPart1 = new PartTypeImpl("EH120",engineCategory);
        PartType requiredPart2 = new PartTypeImpl("TC120",transmissionCategory);
        variants.add(requiredPart1);
        variants.add(requiredPart2);

        CompatibilityManager compatibilityManager = new CompatibilityManagerImpl();
        compatibilityManager.addIncompatibilities(enginePart1,Set.of(incompatiblePart));
        compatibilityManager.addRequirements(requiredPart1,Set.of(requiredPart2));

        //Configurator
        configurator = new ConfiguratorImpl(compatibilityManager,categories, variants);


    }

    @DisplayName("is Complete fail")
    @Test
    public void isCompleteFailTest() {

        Set<PartType> selectedParts = new HashSet<>();
        PartType lastPart = null;

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                selectedParts.add(part);
                lastPart = part;
                break;
            }
        }
        
        selectedParts.remove(lastPart);

        ConfigurationImpl configuration = new ConfigurationImpl(configurator,selectedParts);

        assertFalse(configuration.isComplete());
    }

    @DisplayName("is Complete Success")
    @Test
    public void isCompleteSuccessTest() {
        Set<PartType> selectedParts = new HashSet<>();


        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                selectedParts.add(part);
                break;
            }
        }


        ConfigurationImpl configuration = new ConfigurationImpl(configurator, selectedParts);

        assertTrue(configuration.isComplete());

    }

    @DisplayName("is Valid")
    @Test
    public void isValidTest() {
        Set<PartType> selectedParts = new HashSet<>();

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (part.getName().equals("EG100") || part.getName().equals("TM5")){
                    selectedParts.add(part);
                    break;
                }
            }
        }

        ConfigurationImpl configuration = new ConfigurationImpl(configurator,selectedParts);
        assertTrue(configuration.isValid());
    }

    @DisplayName("is Valid Incompatibility")
    @Test
    public void isValidIncompatibilityTest() {
        Set<PartType> selectedParts = new HashSet<>();

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (part.getName().equals("EG100") || part.getName().equals("TA5")){
                    selectedParts.add(part);
                    break;
                }
            }
        }

        ConfigurationImpl configuration = new ConfigurationImpl(configurator,selectedParts);
        assertFalse(configuration.isValid());
    }

    @DisplayName("is Valid Requirement OK")
    @Test
    public void isValidRequirementOKTest() {
        Set<PartType> selectedParts = new HashSet<>();

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (part.getName().equals("EH120") || part.getName().equals("TC120")){
                    selectedParts.add(part);
                    break;
                }
            }
        }

        ConfigurationImpl configuration = new ConfigurationImpl(configurator,selectedParts);
        assertTrue(configuration.isValid());
    }

    @DisplayName("is Valid Requirement Missing")
    @Test
    public void isValidRequirementMissingTest() {
        Set<PartType> selectedParts = new HashSet<>();

        for(Category cat : configurator.getCategories()){
            for(PartType part : configurator.getVariants(cat)){
                if (part.getName().equals("EH120") || part.getName().equals("TA5")){
                    selectedParts.add(part);
                    break;
                }
            }
        }

        ConfigurationImpl configuration = new ConfigurationImpl(configurator,selectedParts);
        assertFalse(configuration.isValid());
    }
}
