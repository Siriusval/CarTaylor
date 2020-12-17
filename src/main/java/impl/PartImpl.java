package impl;

import api.Category;
import api.Part;
import api.PartType;
import api.Visitor;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represent a Part, an instance of a PartImpl
 */
public class PartImpl implements Part {

    /** The original PartType */
    private final PartType type;

    /**
     * Constructor for PartImpl
     * @param type, the linked PartType
     */
    public PartImpl(PartType type) {
        this.type = type;
    }

    @Override
    public Category getCategory() {
        return type.getCategory();
    }

    @Override
    public PartType getType() {
        return type;
    }

    private class Property {
        /** Getter for property*/
        public final Supplier<String> getter;
        /** Setter for property*/
        public final Consumer<String> setter;
        /** Possible values for property*/
        public final Set<String> possibleValues;

        /**
         * Constructor for Property
         * @param getter, the getter for the property
         * @param setter, the setter for the property
         * @param possibleValues, available values for this property
         */
        Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibleValues) {
            this.getter = getter;
            this.setter = setter;
            this.possibleValues = possibleValues;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Property property = (Property) o;
            //return Objects.equals(getter, property.getter) && Objects.equals(setter, property.setter) && Objects.equals(possibleValues, property.possibleValues);
            return Objects.equals(possibleValues, property.possibleValues);
        }

        @Override
        public int hashCode() {
            return Objects.hash(possibleValues);
        }
    }

    /** List of properties for the partImpl*/
    private final Map<String, Property> properties = new HashMap<>();


    /**
     * Add a property to the instance
     * @param name, name of the property
     * @param getter, getter method to get the property
     * @param setter, setter method to change the property
     * @param possibleValues, set of possible values
     */
    protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter,
                               Set<String> possibleValues) {
        properties.put(name, new Property(getter, setter, possibleValues));
    }

    @Override
    public Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    @Override
    public Optional<String> getProperty(String propertyName) {
        Objects.requireNonNull(propertyName);

        if (properties.containsKey(propertyName)) {
            return Optional.of(properties.get(propertyName).getter.get());
        }
        return Optional.empty();
    }

    @Override
    public void setProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Objects.requireNonNull(propertyValue);

        if ((properties.containsKey(propertyName)) && (properties.get(propertyName).setter != null)) {
            properties.get(propertyName).setter.accept(propertyValue);
        } else {
            throw new IllegalArgumentException("bad property name or value: " + propertyName);
        }
    }

    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartImpl part = (PartImpl) o;
        return Objects.equals(type, part.type) && Objects.equals(properties, part.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, properties);
    }

    public void accept(Visitor v){
        v.visitPart(this);
    }
}
