import api.*;
import impl.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

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
class CompatibilityManagerTest {

    public static Logger log ;
    public static CompatibilityManager cm;

    //Parts to use while testing
    public PartType EG100;
    public PartType ED180;
    public PartType XS;
    public PartType IS;

    @BeforeAll
    public static void setup() {
        log = Logger.getLogger(CompatibilityManagerTest.class.getName());
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    public void init() {
        log.info("@BeforeEach - executes before each test method in this class");

        //Create or Reset cm
        cm = new CompatibilityManagerImpl();

        //Create EG100
        Category engineCategory = new CategoryImpl("engine");
        EG100 = new PartTypeImpl("EG100", part.Engine.class, engineCategory);

        //Doesnt have any incompatibilities or requirements
        ED180 = new PartTypeImpl("ED180",part.Engine.class,engineCategory);

        //Create XS
        Category exteriorCategory = new CategoryImpl("exterior");
        XS = new PartTypeImpl("XS", part.Exterior.class,exteriorCategory);

        //Add incompatibility
        cm.addIncompatibilities(XS,Set.of(EG100));

        //Create IS
        Category interiorCategory = new CategoryImpl("interior");
        IS = new PartTypeImpl("IS", part.Interior.class,interiorCategory);

        //Add requirement
        cm.addRequirements(XS,Set.of(IS));
    }


    /**
     * Test if the incompatibilities were added and returned<br>
     * Test for symmetry
     */
    @DisplayName("Add/Get Incompatibility Test")
    @Test
    void addIncompatibility(){
        log.info("Add/Get Incompatibility Test");

        //Check if contained
        assertTrue(cm.getIncompatibilities(EG100).contains(XS));
        assertTrue(cm.getIncompatibilities(XS).contains(EG100));
    }

    /**
     * Test if the requirements were added and returned<br>
     * Test for non-symmetry
     */
    @DisplayName("Add/Get Requirement Test")
    @Test
    void addRequirement(){
        log.info("Add/Get Requirement Test");

        //Check if contained
        assertTrue(cm.getRequirements(XS).contains(IS));
        assertFalse(cm.getRequirements(IS).contains(XS));
    }

    /**
     * Get the incompatibilities of a part with no constraints<br>
     * Should return an emptySet
     */
    @DisplayName("Get No Incompatibility Test")
    @Test
    void getNoIncompatibilitiesTest(){
        log.info("Get No Incompatibility Test");

        //Check if contained
        assertTrue(cm.getIncompatibilities(ED180).isEmpty());
    }


    /**
     * Get the requirements of a part with no constraints<br>
     * Should return an emptySet
     */
    @DisplayName("Get No Requirement Test")
    @Test
    void getNoRequirementsTest(){
        log.info("Get No Requirement Test");

        //Check if contained
        assertTrue(cm.getRequirements(ED180).isEmpty());
    }

    /**
     * Test adding an emptySet of incompatibilities<br>
     * Should throw an error
     */
    @DisplayName("Add Incompatibility Empty Set")
    @Test
    void addIncompatibilityEmptySet(){
        log.info("Add Incompatibility Test");

        Executable exec = () -> {
            //Add incompatibility
            cm.addIncompatibilities(EG100,new HashSet<>());
        };

        //Check if contained
        assertThrows(IllegalArgumentException.class,exec);
    }

    /**
     * Test adding an emptySet of requirements<br>
     * Should throw an error
     */
    @DisplayName("Add Requirement Empty Set")
    @Test
    void addRequirementEmptySet(){
        log.info("Add Requirement Test");

        Executable exec = () -> {
            //Add Requirement
            cm.addRequirements(EG100,new HashSet<>());
        };

        //Check if contained
        assertThrows(IllegalArgumentException.class,exec);
    }

    /**
     * Test for removing an incompatibility
     */
    @DisplayName("Remove Incompatibility Test")
    @Test
    void removeIncompatibilityTest(){
        log.info("Remove Incompatibility Test");

        //Choose the one to remove
        cm.removeIncompatibility(XS,EG100);

        //Get incompatibilities
        Set<PartType> incompatibilities = cm.getIncompatibilities(XS);

        assertFalse(incompatibilities.contains(EG100));
    }

    /**
     * Test for removing a requirement
     */
    @DisplayName("Remove Requirement Test")
    @Test
    void removeRequirementTest(){
        log.info("Remove Requirement Test");


        //Choose the one to remove
        cm.removeRequirement(XS,IS);

        //Get requirements
        Set<PartType> requirements = cm.getRequirements(XS);

        assertFalse(requirements.contains(IS));
    }

}