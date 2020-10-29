package impl;

import api.Category;
import java.util.Objects;

/**
 * Implementation of the Category Interface
 *
 * @see Category
 * @author Valentin Hulot
 */
public class CategoryImpl implements Category {

    /** Name of the category */
    private final String name;

    /**
     * Constructor for CategoryImpl<br>
     * pre : String must not be null or empty
     * @throws NullPointerException if String is null
     * @throws IllegalArgumentException if String is empty
     * @param name, the name of the category
     */
    public CategoryImpl(String name) throws IllegalArgumentException {
        Objects.requireNonNull(name);
        if(name.isBlank()){
            throw new IllegalArgumentException("String cannot be empty");
        }

        this.name = name;
    }

    /**
     * {@inheritDoc}
     * @return string name
     */
    @Override
    public String getName() {
        return this.name;
    }


    /**
     * Check if both Categories are equal
     * @param o, the other object to check
     * @return True if equal, else False
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryImpl category = (CategoryImpl) o;

        return name.equals(category.name);
    }

    /**
     * Generate hashcode to compare two objects of this class
     * @return the object hashcode
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
