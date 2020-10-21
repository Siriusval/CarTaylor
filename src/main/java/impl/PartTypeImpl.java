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

    /**
     * Check if both PartTypes are equal
     * @param o, the other object to check
     * @return True if equal, else False
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartTypeImpl partType = (PartTypeImpl) o;

        if (!name.equals(partType.name)) return false;
        return category.equals(partType.category);
    }

    /**
     * Generate hashcode to compare two objects of this class
     * @return the object hashcode
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
