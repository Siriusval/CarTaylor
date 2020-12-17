package impl;

import api.Category;
import api.Configuration;
import api.Part;
import api.PartType;

public interface Visitor {

    void visitConfiguration(Configuration c);
    void visitCategory(Category c);
    void visitPartType(PartType c);
    void visitPart(Part c);

}
