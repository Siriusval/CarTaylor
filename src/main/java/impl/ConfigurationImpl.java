package impl;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

import java.io.PrintStream;
import java.util.Set;

/**
 * Implementation class of interface  Configuration
 *
 * @see Configuration
 * @author Valentin Hulot
 */
public class ConfigurationImpl implements Configuration {

    private Set<PartType> selectedParts;

    public ConfigurationImpl(Set<PartType> selectedParts) {
        this.selectedParts = selectedParts;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public Set<PartType> getSelectedParts() {
        return null;
    }

    @Override
    public void selectPart(PartType chosenPart) {

    }

    @Override
    public PartType getSelectionForCategory(Category category) {
        return null;
    }

    @Override
    public void unselectPartType(Category categoryToClear) {

    }

    @Override
    public void clear() {

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
