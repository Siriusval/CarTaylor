package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Engine extends  PartImpl{


    public Engine(PartTypeImpl pti) {
        super(pti);
        addProperty("type", () -> type(pti.getName()), null, Set.of(type(pti.getName())));
    }

    private String type(String name) {
        switch (name) {
            case "EG100":
                return "diesel";
            case "EG133":
                return "diesel";
            case "EG210":
                return "diesel";
            case "ED110":
                return "essence";
            case "ED180":
                return "essence";
            case "EH120":
                return "hybrid";
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }
}
