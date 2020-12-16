package impl;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

import java.util.*;

/**
 * Implementation class of interface  Configuration
 *
 * @see Configuration
 * @author Valentin Hulot
 */
public class ConfigurationImpl implements Configuration {

    /** Set of parts chosen in the configuration */
    private final Set<Part> selectedParts;
    /** Reference to the configurator so we can check constraints on parts */
    private final ConfiguratorImpl configuratorRef;


    /**
     * Constructor for ConfigurationImpl
     * @param configuratorRef, the configurator (need its operations for checking constraints)
     * @param selectedParts, the set of selectedParts
     */
    public ConfigurationImpl(ConfiguratorImpl configuratorRef, Set<Part> selectedParts) {

        Objects.requireNonNull(selectedParts,"selectedParts cannot be null");
        Objects.requireNonNull(configuratorRef,"configuratorRef cannot be null");

        this.selectedParts = selectedParts;
        this.configuratorRef = configuratorRef;
    }

    /**
     * {@inheritDoc}
     * @return if config is valid (aka. contains requirements, don't contains incompatibilities)
     */
    @Override
    public boolean isValid() {

        //Check constraints
        for (Part part : this.selectedParts){ //For each part
            //Check requirements
            Set<PartType> requirements = this.configuratorRef.getCompatibilityChecker().getRequirements(part.getType());

            //get all partType from part
            Set<PartType> selectedPartTypes = new HashSet<>();
            getSelectedParts().forEach( (p) -> selectedPartTypes.add(p.getType()));

            if (!selectedPartTypes.containsAll(requirements)){ //check if requirements are missing in the config
                return false;
            }

            //Check incompatibilities
            Set<PartType> incompatibilities = this.configuratorRef.getCompatibilityChecker().getIncompatibilities(part.getType());


            if (!Collections.disjoint(selectedPartTypes,incompatibilities)) { //check if incompatibilities are in the config
                return false;
            }

        }

        return true;
    }

    /**
     * {@inheritDoc}
     * @return if configuration is complete (contains a part for each category)
     */
    @Override
    public boolean isComplete() {
        Set<Category> usedCategories = new HashSet<>();

        //List all used categories
        for(Part part : selectedParts){
            usedCategories.add(part.getCategory());
        }

        //Check if our categories contains all the mandatory categories (return false if al least one is missing)
        return usedCategories.containsAll(this.configuratorRef.getCategories());

    }

    /**
     * {@inheritDoc}
     * @return the part associated to the category
     */
    @Override
    public Set<Part> getSelectedParts() {
        return this.selectedParts;
    }

    /**
     * {@inheritDoc}
     * @param chosenPart, the part to be selected
     */
    @Override
    public void selectPart(PartType chosenPart) {
        Objects.requireNonNull(chosenPart,"chosenPart must not be null");

        //remove old part in same cat
        unselectPartType(chosenPart.getCategory());
        PartTypeImpl pti = (PartTypeImpl) chosenPart;
        this.selectedParts.add(pti.newInstance());
    }

    /**
     * {@inheritDoc}
     * @param category, the category of the part we want
     * @return the part selected for a specific category
     */
    @Override
    public Optional<Part> getSelectionForCategory(Category category) {

        Objects.requireNonNull(category,"category cannot be null");

        for(Part part : this.selectedParts){ //Search for category
            if (part.getCategory().equals(category)){
                return Optional.of(part); //get part
            }
        }

        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     * @param categoryToClear, the category from which we remove the part
     */
    @Override
    public void unselectPartType(Category categoryToClear) {

        Objects.requireNonNull(categoryToClear,"categoryToClear cannot be null");

        //Search for category & remove part
        this.selectedParts.removeIf(part -> part.getCategory() == categoryToClear);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.selectedParts.clear();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConfiguration(this);
    }

    /*
        @Override
        public void printDescription(PrintStream stream) {
            stream.println("isValid : "+this.isValid());
            stream.println("isComplete : "+this.isComplete());
            for (Part part : selectedParts){
                stream.println(part.printDescription());
            }
        }
        */

    /*
     * Check if both Configurations are equal
     * @param o, the other object to check
     * @return True if equal, else False
     */
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigurationImpl that = (ConfigurationImpl) o;

        if (!selectedParts.equals(that.selectedParts)) return false;
        return configuratorRef.equals(that.configuratorRef);
    }
    */

    /*
     * Generate hashcode to compare two objects of this class
     * @return the object hashcode
     */
    /*
    @Override
    public int hashCode() {
        int result = selectedParts.hashCode();
        result = 31 * result + configuratorRef.hashCode();
        return result;
    }
    */
}
