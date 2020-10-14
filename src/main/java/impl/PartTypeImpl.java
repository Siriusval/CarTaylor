package impl;

import api.Category;
import api.PartType;

/**
 * Implementation class of PartType
 *
 * @see PartType
 * @author Valentin Hulot
 */
public class PartTypeImpl implements PartType {

    private String name;
    private Category category;

    public PartTypeImpl(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Category getCategory() {
        return null;
    }
}
