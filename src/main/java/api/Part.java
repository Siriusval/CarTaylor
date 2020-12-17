package api;

/**
 * Interface that represents a Part with properties
 */
public interface Part extends PropertyManager{

    /**
     * Get name of the instance class of the Part
     * @return the name of the class
     */
    default String getName() {
        return this.getClass().getTypeName();
    }

    /**
     * Get category of the part
     * @return the category
     */
    Category getCategory();

    /**
     * Get partType
     * @return the partType
     */
    PartType getType();

    /**
     * Accept visitor
     * @param v, the visitor
     */
    void accept(Visitor v);
}
