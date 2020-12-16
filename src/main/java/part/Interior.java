package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Interior extends  PartImpl{

    public Interior(PartTypeImpl pti) {
        super(pti);
        addProperty("finish", () -> finish(pti.getName()) , null, Set.of(finish(pti.getName())));
    }

    public String finish(String name){
        switch (name){
            case "IN":
                return "Standard interior";
            case "IH":
                return "High-end interior";
            case "IS":
                return "Sport finish";
            default :
                return null;
        }
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
