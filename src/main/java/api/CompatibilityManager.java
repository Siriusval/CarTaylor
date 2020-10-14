package api;

import java.util.Set;

/**
 * Interface CompatibilityManager<br>
 * A public type to get manage (add and remove) constraints between parts<br>
 *
 * @author Valentin Hulot
 */
public interface CompatibilityManager extends CompatibilityChecker {

    /**
     * Add a set of incompatibilites to a specific part
     * @param reference, the part to which we add the incompatibilities
     * @param target, the parts that are incompatibles with the reference
     */
    void addIncompatibilities(PartType reference, Set<PartType> target);

    /**
     * Remove one incompatibility from a specific part
     * @param reference, the part to which we remove the incompatibility
     * @param target, the part that is no longer incompatible with the reference
     */
    void removeIncompatibility(PartType reference, PartType target);

    /**
     * Add a set of requirements to a specific part
     * @param reference, the part to which we add the requirements
     * @param target, the parts that are required with the reference
     */
    void addRequirements(PartType reference, Set<PartType> target);

    /**
     * Remove one requirement from a specific part
     * @param reference, the part to which we remove the requirement
     * @param target, the part that is no longer required with the reference
     */
    void removeRequirement(PartType reference, PartType target);

}
