package fr.istic.vhulot.cartaylor.api;

import java.util.Set;

/**
 * Interface Configurator
 * Expose the necessary methods to get :
 * _ the categories
 * _ variant
 * _ configuration
 * _ compatibilityChecker objects
 *
 * @author  Valentin Hulot
 */
public interface Configurator {

    /**
     * Get all the categories
     * @return, a set of Categories
     */
    Set<Category> getCategories();

    /**
     * Get all the variants (parts) for a specific category
     *
     * @param category, the category of the elements we want to retrieve
     * @return the set of all the parts in the specified category
     */
    Set<PartType> getVariants(Category category);

    /**
     * Get the Configuration
     * @return the configuration object
     */
    Configuration getConfiguration();

    /**
     * Get the compatibilityChecker
     * @return the CompatibilityChecker object
     */
    CompatibilityChecker getCompatibilityChecker();

}
