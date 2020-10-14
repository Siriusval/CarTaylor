package api;

/**
 * Interface of PartType
 * Contains the methods to implement in PartType
 * Allows to get the caracteristics of a PartType
 */
public interface PartType {

    /**
     * Get the name of the part
     * @return the name of the part
     */
    String getName();

    /**
     * Get the category to which the part belongs
     * @return the category to which the part belongs
     */
    Category getCategory();
}
