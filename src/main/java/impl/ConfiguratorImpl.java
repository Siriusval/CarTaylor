package impl;

import api.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class of interface Configurator
 * It makes available the key objects / methods
 *
 * @see Configurator
 * @author Valentin Hulot
 */
public class ConfiguratorImpl implements Configurator {

    /** Configuration object, represent a configuration of partTypes  */
    private final ConfigurationImpl configuration;
    /** CompatibilityChecker object, manage requirements and incompatibilities */
    private final CompatibilityChecker compatibilityChecker;
    /** Set of all the categories */
    private final Set<Category> categories;
    /** Set of all the variants (PartTypes) */
    private final Set<PartType> variants;

    /**
     * Constructor for ConfiguratorImpl
     * @param configuration, the configuration
     * @param compatibilityChecker, the compatibilityChecker
     * @param categories, set of categories
     * @param variants, set of variants/partTypes
     */
    public ConfiguratorImpl(ConfigurationImpl configuration, CompatibilityChecker compatibilityChecker, Set<Category> categories, Set<PartType> variants) {

        Objects.requireNonNull(configuration,"configuration cannot be null");
        Objects.requireNonNull(compatibilityChecker,"compatibilityChecker cannot be null");
        Objects.requireNonNull(categories,"categories cannot be null");
        Objects.requireNonNull(variants,"variants cannot be null");

        this.configuration = configuration;
        this.compatibilityChecker = compatibilityChecker;
        this.categories = categories;
        this.variants = variants;
    }

    /**
     * {@inheritDoc}
     * @return a copy of the set of categories
     */
    @Override
    public Set<Category> getCategories() {

        return Set.copyOf(this.categories);
    }

    /**
     * {@inheritDoc}
     * @param category, the category of the elements we want to retrieve
     * @return a copy of the set of variants
     */
    @Override
    public Set<PartType> getVariants(Category category) {
        Objects.requireNonNull(category,"category cannot be null");

        return this.variants.stream().filter(v -> v.getCategory()== category).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     * @return teh configuration
     */
    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * {@inheritDoc}
     * @return the compatibilityChecker
     */
    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return this.compatibilityChecker;
    }
}
