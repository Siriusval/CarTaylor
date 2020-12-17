package api;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

/**
 * Visitor interface for the prettyPrinter
 */
public interface Visitor {

    /**
     * Visit configuration
     * @param c, the configuration
     */
    void visitConfiguration(Configuration c);

    /**
     * Visit category
     * @param c, the category
     */
    void visitCategory(Category c);

    /**
     * Visit partType
     * @param p, the partType
     */
    void visitPartType(PartType p);

    /**
     * Visit part
     * @param p, the part
     */
    void visitPart(Part p);

}
