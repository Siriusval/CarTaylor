package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Set;

public class Transmission extends  PartImpl{

    public Transmission(PartTypeImpl pti) {
        super(pti);
        addProperty("transmission", () -> transmission(pti.getName()) , null, Set.of(transmission(pti.getName())));
        addProperty("speed", () -> speed(pti.getName()) , null, Set.of(speed(pti.getName())));
    }

    public String transmission(String name){
        switch (name){
            case "TM5":
            case "TM6":
                return "Manual";
            case "TA5":
                return "Automatic";
            case "TS6":
            case "TSF7":
                return "Sequential";
            case "TC120":
                return "Converter";
            default :
                return null;
        }
    }

    public String speed(String name){
        switch (name){
            case "TM5":
            case "TA5":
                return "5 gears";
            case "TM6":
            case "TS6":
                return "6 gears";
            case "TSF7":
                return "7 gears, 4 wheels drive";
            case "TC120":
                return "120kW max";
            default :
                return null;
        }
    }



    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
