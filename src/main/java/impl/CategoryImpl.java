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
            throw new IllegalArgumentException("String must not be empty");
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

}
