package impl;

import api.Category;
import api.Part;
import api.PartType;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*
 * Snippet to add a basic implementation of PropertyManager
 */
public class PartImpl implements Part {

    private final PartType type;

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
        public final Supplier<String> getter;
        public final Consumer<String> setter;
        public final Set<String> possibleValues;

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

    private final Map<String, Property> properties = new HashMap<>();



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
