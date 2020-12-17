package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

/**
 * Class that represents all the different interior Parts (for instantiation)
 */
public abstract class Interior extends  PartImpl{

    /** Constant for property name */
    public static final String STYLE_PROPERTY = "style";

    /**Enum for all the different interior styles */
    public enum InteriorStyle {
        STANDARD,
        HIGH_END,
        SPORT
    }

    /** Interior style */
    protected InteriorStyle interiorStyle;
    /** Possible Interior styles */
    protected Set<String> possibleInteriorStyle;

    /**
     * Constructor for the interior partImpl
     * @param type the linked partType
     */
    private Interior(PartType type) {
        super(type);
    }

    /**
     * Get interior style
     * @return the interiorStyle
     */
    protected String getInteriorStyle() {
        return interiorStyle.name();
    }

    /**
     * Set interiorStyle
     * @param value, the new value
     */
    protected void setInteriorStyle(String value) {
        assert getAvailablePropertyValues(STYLE_PROPERTY).contains(value);
        this.interiorStyle = InteriorStyle.valueOf(value);
    }

    /**
     * Model for the IN instance
     */
    public static class IN extends Interior{
        /**
         * Constructor for the instance
         * @param pti, the partType linked to this instance
         */
        public IN(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.STANDARD;
            this.possibleInteriorStyle = Set.of(InteriorStyle.STANDARD.name());
            addProperty(STYLE_PROPERTY, this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
        }
    }

    /**
     * Model for the IH instance
     */
    public static class IH extends Interior{
        /**
         * Constructor for the instance
         * @param pti, the partType linked to this instance
         */
        public IH(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.HIGH_END;
            this.possibleInteriorStyle = Set.of(InteriorStyle.HIGH_END.name());
            addProperty(STYLE_PROPERTY, this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
        }
    }

    /**
     * Model for the IS instance
     */
    public static class IS extends Interior{
        /**
         * Constructor for the instance
         * @param pti, the partType linked to this instance
         */
        public IS(PartTypeImpl pti) {
            super(pti);
            this.interiorStyle = InteriorStyle.SPORT;
            this.possibleInteriorStyle = Set.of(InteriorStyle.SPORT.name());
            addProperty(STYLE_PROPERTY, this::getInteriorStyle, this::setInteriorStyle, possibleInteriorStyle);
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
