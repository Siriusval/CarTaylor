package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Transmission extends  PartImpl{


    public Transmission(PartTypeImpl pti) {
        super(pti);
        addProperty("speed", () -> speed(pti.getName()), null, Set.of(speed(pti.getName())));
    }

    private String speed(String name) {
        switch (name) {
            case "TM5":
                return "5";
            case "TM6":
                return "6";
            case "TA5":
                return "6";
            case "TS6":
                return "5";
            case "TSF7":
                return "5";
            case "TC120":
                return "4";
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
