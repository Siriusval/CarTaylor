package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Interior extends  PartImpl{


    public Interior(PartTypeImpl pti) {
        super(pti);
        addProperty("color", () -> color(pti.getName()), null, Set.of(color(pti.getName())));
    }

    private String color(String name) {
        switch (name) {
            case "IN":
                return "brown";
            case "IH":
                return "white";
            case "IS":
                return "black";
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }
}
