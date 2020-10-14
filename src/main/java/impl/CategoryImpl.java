package impl;

import api.Category;

/**
 * Implementation of the Category Interface
 *
 * @see Category
 * @author Valentin Hulot
 */
public class CategoryImpl implements Category {

    private String name;

    public CategoryImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
