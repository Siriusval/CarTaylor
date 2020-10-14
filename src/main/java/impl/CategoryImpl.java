package impl;

import api.Category;

/**
 * Implementation of the Category Interface
 *
 * @see Category
 * @author Valentin Hulot
 */
public class CategoryImpl implements Category {

    /** Name of the category */
    private String name;

    /**
     * Constructor for CategoryImpl
     * @param name, the name of the category
     */
    public CategoryImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
