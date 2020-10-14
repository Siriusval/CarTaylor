package impl;

import api.*;

import java.util.Set;

/**
 * Implementation class of interface Configurator
 *
 * @see Configurator
 * @author Valentin Hulot
 */
public class ConfiguratorImpl implements Configurator {
    @Override
    public Set<Category> getCategories() {
        return null;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return null;
    }
}
