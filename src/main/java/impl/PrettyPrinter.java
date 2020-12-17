package impl;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

import java.io.PrintStream;
import java.util.Optional;

public class PrettyPrinter implements Visitor {

    private final PrintStream output;


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
