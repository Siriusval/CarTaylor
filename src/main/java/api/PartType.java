package api;

/**
 * Interface of PartType<br>
 * Contains the methods to implement in PartType<br>
 * Allows to get the characteristics of a PartType
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

    /**
     * Accept a visitor
     * @param v, the visitor
     */
    void accept(Visitor v);
}
