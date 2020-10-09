package fr.istic.vhulot.cartaylor.impl;

import fr.istic.vhulot.cartaylor.api.Category;
import fr.istic.vhulot.cartaylor.api.Configuration;
import fr.istic.vhulot.cartaylor.api.PartType;

import java.util.Set;

public class ConfigurationImpl implements Configuration {
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
}
