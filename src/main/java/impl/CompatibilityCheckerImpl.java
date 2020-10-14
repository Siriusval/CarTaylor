package impl;

import api.CompatibilityChecker;
import api.PartType;

import java.util.Set;

/**
 * Implementation class of CompatibilityChecker
 *
 * @see CompatibilityChecker
 * @author Valentin Hulot
 */
public class CompatibilityCheckerImpl implements CompatibilityChecker {
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return null;
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return null;
    }
}
