package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Optional;
import java.util.Set;

public class Engine extends  PartImpl{

    public Engine(PartTypeImpl pti) {
        super(pti);
        addProperty("type", () -> type(pti.getName()) , null, Set.of(type(pti.getName())));
        addProperty("power", () -> power(pti.getName()) , null, Set.of(power(pti.getName())));
    }

    public String type(String name){
        switch (name){
            case "EG100":
            case "EG133":
            case "EG210":
                return "Gasoline";
            case "ED110":
            case "ED180":
                return "Diesel";
            case "EH120":
                return "Gasoline/electric hybrid";
            default :
                return null;
        }
    }

    public String power(String name){
        switch (name){
            case "EG100":
                return "100kW";
            case "EG133":
                return "133kW";
            case "EG210":
                return "210kW";
            case "ED110":
                return "110kW";
            case "ED180":
                return "180kW";
            case "EH120":
                return "120kW";
            default :
                return null;
        }
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
