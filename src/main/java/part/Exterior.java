package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Exterior extends  PartImpl{


    public Exterior(PartTypeImpl pti) {
        super(pti);
        addProperty("color", () -> color(pti.getName()), null, Set.of(color(pti.getName())));
    }

    private String color(String name) {
        switch (name) {
            case "XC":
                return "blue";
            case "XM":
                return "red";
            case "XS":
                return "green";
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }
}
