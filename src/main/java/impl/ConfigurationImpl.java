package impl;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation class of interface  Configuration
 *
 * @see Configuration
 * @author Valentin Hulot
 */
public class ConfigurationImpl implements Configuration {

    /** Set of parts choosen in the configuration */
    private Set<PartType> selectedParts;
    /** Reference to the configurator so we can check constraints on parts */
    private ConfiguratorImpl configuratorRef;


    /**
     * Constructor for ConfigurationImpl
     * @param selectedParts, the set of selectedParts
     * @param configuratorRef, the reference to the configurator
     */
    public ConfigurationImpl(Set<PartType> selectedParts, ConfiguratorImpl configuratorRef) {
        this.selectedParts = selectedParts;
        this.configuratorRef = configuratorRef;
    }

    @Override
    public boolean isValid() {

        //Check is complete
        if (!this.isComplete()){
            return false;
        }

        //Check requirements
        for (PartType part : this.selectedParts){ //For each part
            Set<PartType> requirements = this.configuratorRef.getCompatibilityChecker().getRequirements(part);

            if (!this.selectedParts.containsAll(requirements)){ //check if requirements are missing in the config
                return false;
            }
        }

        //Check incompatibilities
        for (PartType part : this.selectedParts){ //For each part
            Set<PartType> incompatibilities = this.configuratorRef.getCompatibilityChecker().getIncompatibilities(part);

            if (!Collections.disjoint(this.selectedParts,incompatibilities)) { //check if incompatibilities are in the config
                return false;
            }
        }



        return true;
    }

    @Override
    public boolean isComplete() {
        Set<Category> usedCategories = new HashSet<>();

        //List all used categories
        for(PartType part : selectedParts){
            usedCategories.add(part.getCategory());
        }

        //Check if one category is missing
        if(!usedCategories.containsAll(this.configuratorRef.getCategories())){
            return false;
        }

        return true;

    }

    @Override
    public Set<PartType> getSelectedParts() {
        return this.selectedParts;
    }

    @Override
    public void selectPart(PartType chosenPart) {
        //remove old part in same cat
        unselectPartType(chosenPart.getCategory());
        this.selectedParts.add(chosenPart);
    }

    @Override
    public PartType getSelectionForCategory(Category category) {

        for(PartType part : this.selectedParts){ //Search for category
            if (part.getCategory() == category){
                return part; //get part
            }
        }

        return null;
    }

    @Override
    public void unselectPartType(Category categoryToClear) {

        //Search for category & remove part
        this.selectedParts.removeIf(part -> part.getCategory() == categoryToClear);
    }

    @Override
    public void clear() {
        this.selectedParts.clear();
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

}
