package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Exterior extends  PartImpl{

    public Exterior(PartTypeImpl pti) {
        super(pti);
        addProperty("paint", () -> paint(pti.getName()) , null, Set.of(paint(pti.getName())));
    }

    public String paint(String name){
        switch (name){
            case "XC":
                return "Classic paint";
            case "XM":
                return "Metallic paint";
            case "XS":
                return "Red paint and sport decoration";
            default :
                return null;
        }
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
