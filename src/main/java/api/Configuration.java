    package api;


import java.io.PrintStream;
import java.util.Set;

/**
 * Interface Configuration
 * Represent a configuration choosen by the customer
 * Check the state of a configuration, select and unselect parts
 *
 * @author  Valentin Hulot
 */
public interface Configuration {

    /**
     * Check if the configuration is valid
     *
     * @return True if it is valid
     *          else False
     */
    boolean isValid();

    /**
     * Check if the configuration is complete
     *
     * @return True if is complete
     */
    boolean isComplete();

    /**
     * Get the selected part in the configuration
     *
     * @return a set of the selected parts
     */
    Set<PartType> getSelectedParts();

    /**
     * Select a part (add it to the configuration)
     *
     * @param chosenPart, the part to be selected
     */
    void selectPart(PartType chosenPart);

    /**
     * For a specific category, get the part selected
     * @param category, the category of the part we want
     * @return the part that belongs to this categ
     */
    PartType getSelectionForCategory(Category category);

    /**
     * Unselect a part
     * @param categoryToClear, the category from which we remove the part
     */
    void unselectPartType(Category categoryToClear);

    /**
     * Remove all the part of the configuration
     */
    void clear();

    /**
     * Print description of the configuration
     * @param stream, the stream that print the configuration
     */
    //void printDescription(PrintStream stream) ;

}
