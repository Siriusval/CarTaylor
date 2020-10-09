package fr.istic.vhulot.cartaylor.impl;

import fr.istic.vhulot.cartaylor.api.*;

import java.util.Set;

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
