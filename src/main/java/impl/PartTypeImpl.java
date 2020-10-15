package impl;

import api.Category;
import api.PartType;

import java.util.Objects;

/**
 * Implementation class of PartType
 *
 * @see PartType
 * @author Valentin Hulot
 */
public class PartTypeImpl implements PartType {

    /** Name of the partType */
    private final String name;
    /** Category of the partType */
    private final Category category;

    /**
     * Constructor for partTypes
     * @param name, the name of the partType
     * @param category, the category of the partType
     */
    public PartTypeImpl(String name, Category category) {

        Objects.requireNonNull(name,"name cannot be null");
        Objects.requireNonNull(category,"category cannot be null");
        if(name.isBlank()){
            throw new IllegalArgumentException("String cannot be empty");
        }

        this.name = name;
        this.category = category;
    }

    /**
     * {@inheritDoc}
     * @return the name of the PartType
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     * @return the Category
     */
    @Override
    public Category getCategory() {
        return this.category;
    }
}
