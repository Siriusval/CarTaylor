package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

public abstract class Interior extends  PartImpl{

    private enum InteriorStyle {
        STANDARD,
        HIGH_END,
        SPORT
    }

    protected InteriorStyle interiorStyle;
    protected Set<String> possibleInteriorStyle;


    private Interior(PartType type) {
        super(type);
    }

    protected String getInteriorStyle() {
        return interiorStyle.name();
    }

    protected void setInteriorStyle(String value) {
        assert getAvailablePropertyValues("style").contains(value);
        this.interiorStyle = InteriorStyle.valueOf(value);
    }

    public static class IN extends Interior{
        public IN(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.STANDARD;
            this.possibleInteriorStyle = Set.of(InteriorStyle.STANDARD.name());
            addProperty("style", this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
        }
    }

    public static class IH extends Interior{
        public IH(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.HIGH_END;
            this.possibleInteriorStyle = Set.of(InteriorStyle.HIGH_END.name());
            addProperty("style", this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
        }
    }

    public static class IS extends Interior{
        public IS(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.SPORT;
            this.possibleInteriorStyle = Set.of(InteriorStyle.SPORT.name());
            addProperty("style", this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Interior interior = (Interior) o;
        return interiorStyle == interior.interiorStyle && Objects.equals(possibleInteriorStyle, interior.possibleInteriorStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), interiorStyle, possibleInteriorStyle);
    }
}
