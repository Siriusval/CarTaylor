package fr.istic.vhulot.cartaylor.api;

import java.util.Set;

/**
 * Interface CompatibilityChecker
 * A public type to get constraints (requirements and incompatibilities)
 * for a specific PartType
 *
 * @author Valentin Hulot
 */
public interface CompatibilityChecker {

    /**
     * Get the incompatibilities for a specified part
     * @param reference, the PartType to be checked for incompatibilities
     * @return the list off all the parts that are incompatibles w/ this part
     */
    Set<PartType> getIncompatibilities(PartType reference);

    /**
     * Get the requirements for a specified part
     * @param reference, the PartType to be checked for requirements
     * @return the list off all the parts that are required w/ this part
     */
    Set<PartType> getRequirements(PartType reference);

}
