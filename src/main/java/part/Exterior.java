package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

public abstract class Exterior extends  PartImpl{

    public static final String PAINT_PROPERTY = "paint";

    private enum PaintColor {
        CLASSIC,
        METALLIC,
        RED
    }

    protected PaintColor paintColor;
    protected Set<String> possiblePaintColor;

    private Exterior(PartType type) {
        super(type);
    }

    protected String getPaintColor() {
        return paintColor.name();
    }

    protected void setPaintColor(String value) {
        assert getAvailablePropertyValues(PAINT_PROPERTY).contains(value);
        this.paintColor = PaintColor.valueOf(value);
    }

    public static class XC extends Exterior{
        public XC(PartTypeImpl pti) {
            super(pti);
            this.paintColor = PaintColor.CLASSIC;
            this.possiblePaintColor = Set.of(PaintColor.CLASSIC.name());
            addProperty(PAINT_PROPERTY, this::getPaintColor, this::setPaintColor, possiblePaintColor);
        }
    }

    public static class XM extends Exterior{
        public XM(PartTypeImpl pti) {
            super(pti);
            this.paintColor = PaintColor.METALLIC;
            this.possiblePaintColor = Set.of(PaintColor.METALLIC.name());
            addProperty(PAINT_PROPERTY, this::getPaintColor, this::setPaintColor, possiblePaintColor);
        }
    }

    public static class XS extends Exterior{
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
