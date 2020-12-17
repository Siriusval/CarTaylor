package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

/**
 * Class that represents all the different exterior Parts (for instantiation)
 */
public abstract class Exterior extends  PartImpl{

    /** Constant for property name */
    public static final String PAINT_PROPERTY = "paint";

    /**Enum for all the different paint colors */
    private enum PaintColor {
        CLASSIC,
        METALLIC,
        RED
    }

    /** Paint color */
    protected PaintColor paintColor;
    /** Possible Paint colors */
    protected Set<String> possiblePaintColor;

    /**
     * Constructor for the instance
     * @param type, the linked partType
     */
    private Exterior(PartType type) {
        super(type);
    }

    /**
     * Get paint color
     * @return the paint color
     */
    protected String getPaintColor() {
        return paintColor.name();
    }

    /**
     * Set paint color
     * @param value, the new value
     */
    protected void setPaintColor(String value) {
        assert getAvailablePropertyValues(PAINT_PROPERTY).contains(value);
        this.paintColor = PaintColor.valueOf(value);
    }

    /**
     * The model for the XC instance
     */
    public static class XC extends Exterior{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public XC(PartTypeImpl pti) {
            super(pti);
            this.paintColor = PaintColor.CLASSIC;
            this.possiblePaintColor = Set.of(PaintColor.CLASSIC.name());
            addProperty(PAINT_PROPERTY, this::getPaintColor, this::setPaintColor, possiblePaintColor);
        }
    }

    /**
     * The model for the XM instance
     */
    public static class XM extends Exterior{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public XM(PartTypeImpl pti) {
            super(pti);
            this.paintColor = PaintColor.METALLIC;
            this.possiblePaintColor = Set.of(PaintColor.METALLIC.name());
            addProperty(PAINT_PROPERTY, this::getPaintColor, this::setPaintColor, possiblePaintColor);
        }
    }

    /**
     * The model for the XS instance
     */
    public static class XS extends Exterior{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public XS(PartTypeImpl pti) {
            super(pti);
            this.paintColor = PaintColor.RED;
            this.possiblePaintColor = Set.of(PaintColor.RED.name());
            addProperty(PAINT_PROPERTY, this::getPaintColor, this::setPaintColor, possiblePaintColor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Exterior exterior = (Exterior) o;
        return paintColor == exterior.paintColor && Objects.equals(possiblePaintColor, exterior.possiblePaintColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paintColor, possiblePaintColor);
    }
}
