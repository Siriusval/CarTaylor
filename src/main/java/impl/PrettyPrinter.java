package impl;

import api.*;

import java.io.PrintStream;
import java.util.Optional;

/**
 * Print the configuration, using a visitor pattern
 */
public class PrettyPrinter implements Visitor {

    /** Output to collect the configuration details */
    private final PrintStream output;

    /**
     * Constructor for pretty printer
     * @param output, the output stream
     */
    public PrettyPrinter(PrintStream output) {
        this.output = output;
    }


    @Override
    public void visitConfiguration(Configuration c) {
        for(Part p: c.getSelectedParts()){
            p.accept(this);
        }
    }

    @Override
    public void visitCategory(Category c) {
        output.println("Category : "+c.getName());
    }

    @Override
    public void visitPartType(PartType pt) {
        output.println("PartType :"+pt.getName());

    }

    @Override
    public void visitPart(Part p) {
        p.getCategory().accept(this);
        p.getType().accept(this);

        output.println("Part :"+p.getName());
        for(String property : p.getPropertyNames()){
            output.print("Property :"+property+", Value :");
            Optional<String> pro = p.getProperty(property);
            output.println(pro.isPresent() ? pro.get() : "Empty");
        }
    }
}
