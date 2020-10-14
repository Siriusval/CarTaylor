package impl;

import api.*;

import java.util.Set;

/**
 * Implementation class of interface Configurator
 * It makes available the key objects / methods
 *
 * @see Configurator
 * @author Valentin Hulot
 */
public class ConfiguratorImpl implements Configurator {

    /** Configuration object, represent a configuration of partTypes  */
    private ConfigurationImpl configuration;
    /** CompatibilityChecker object, manage requirements and incompatibilities */
    private CompatibilityChecker compatibilityChecker;
    /** Set of all the categories */
    private Set<Category> categories;
    /** Set of all the variants (PartTypes) */
    private Set<PartType> variants;

    /**
     * Constructor for ConfiguratorImpl
     * @param configuration, the configuration
     * @param compatibilityChecker, the compatibilityChecker
     * @param categories, set of categories
     * @param variants, set of variants/partTypes
     */
    public ConfiguratorImpl(ConfigurationImpl configuration, CompatibilityChecker compatibilityChecker, Set<Category> categories, Set<PartType> variants) {
        this.configuration = configuration;
        this.compatibilityChecker = compatibilityChecker;
        this.categories = categories;
        this.variants = variants;
    }

    @Override
    public Set<Category> getCategories() {
        return this.categories;
    }

    @Override
    public Set<PartType> getVariants(Category category) {
        return this.variants;
    }

    @Override
    public Configuration getConfiguration() {

        return this.configuration;
    }

    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return this.compatibilityChecker;
    }
}
