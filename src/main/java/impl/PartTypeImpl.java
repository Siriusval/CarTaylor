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

    /** Nmae of the partType */
    private String name;
    /** Category of the partType */
    private Category category;

    /**
     * Constructor for partTypes
     * @param name, the name of the partType
     * @param category, the category of the partType
     */
    public PartTypeImpl(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }
}
