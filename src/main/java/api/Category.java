package api;

import impl.PrettyPrinter;
import impl.Visitor;

/**
 * Interface Category<br>
 * A public type to organize part types in categories<br>
 *
 * @author Valentin Hulot
 */
public interface Category {

    /**
     * Get category name
     * @return the name of the category
     */
    String getName();


    void accept(Visitor v);
}
