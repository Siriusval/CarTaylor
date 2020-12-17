package impl;

import api.Category;
import api.PartType;
import api.Visitor;

import java.lang.reflect.Constructor;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation class of PartType
 *
 * @see PartType
 * @author Valentin Hulot
 */
public class PartTypeImpl implements PartType {

    /** Name of the partType */
    private final String name;

    /** Template for the PartImpl instantiation */
    private final Class<? extends PartImpl> classRef;

    /** Category of the partType */
    private final Category category;

    /**
     * Constructor for partTypes
     * @param name, the name of the partType
     * @param classRef, the class template to create a new instance
     * @param category, the category of the partType
     */
    public PartTypeImpl(String name, Class<? extends PartImpl> classRef, Category category) {

        Objects.requireNonNull(name,"name cannot be null");
        Objects.requireNonNull(category,"category cannot be null");
        if(name.isBlank()){
            throw new IllegalArgumentException("String cannot be empty");
        }

        this.name = name;
        this.classRef = classRef;
        this.category = category;
    }

    /**
     * Create a new instance of a partImpl
     * @return the new instance
     */
    public PartImpl newInstance() {
        Constructor<? extends PartImpl> constructor;
        try {
            Class [] cArg = {PartTypeImpl.class};
            constructor = classRef.getConstructor(cArg);

            return constructor.newInstance(this);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "constructor call failed", e);
            System.exit(-1);
        }
        return null;
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

    @Override
    public void accept(Visitor v) {
        v.visitPartType(this);
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
        return Objects.equals(name, partType.name) && Objects.equals(classRef, partType.classRef) && Objects.equals(category, partType.category);
    }

    /**
     * Generate hashcode to compare two objects of this class
     * @return the object hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, classRef, category);
    }
}
