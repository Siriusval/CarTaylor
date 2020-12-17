package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;
import java.util.Objects;
import java.util.Set;

public abstract class Engine extends PartImpl{

    public static final String POWER_PROPERTY = "power";

    private enum EngineType {
        GASOLINE,
        DIESEL,
        GASOLINE_ELECTRIC_HYBRID
    }

    protected EngineType engineType;
    protected String power;
    protected Set<String> possibleEngineType;
    protected Set<String> possiblePower ;

    private Engine(PartType type) {
        super(type);
    }

    protected String getEngineType() {
        return engineType.name();
    }

    protected void setEngineType(String value) {
        assert getAvailablePropertyValues("engine").contains(value);
        this.engineType = EngineType.valueOf(value);
    }

    protected String getPower() {
        return power;
    }

    protected void setPower(String value) {
        assert getAvailablePropertyValues(POWER_PROPERTY).contains(value);
        this.power = value;
    }


    public static class EG100 extends Engine{
        public EG100(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.GASOLINE;
            this.power = "100kW";
            this.possibleEngineType = Set.of(EngineType.GASOLINE.name());
            this.possiblePower = Set.of("100kW");
            addProperty("type", this::getEngineType, this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    public static class EG133 extends Engine{
        public EG133(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.GASOLINE;
            this.power = "133kW";
            this.possibleEngineType = Set.of(EngineType.GASOLINE.name());
            this.possiblePower = Set.of("133kW");
            addProperty("type", this::getEngineType , this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    public static class EG210 extends Engine{
        public EG210(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.GASOLINE;
            this.power = "210kW";
            this.possibleEngineType = Set.of(EngineType.GASOLINE.name());
            this.possiblePower = Set.of("210kW");
            addProperty("type", this::getEngineType, this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    public static class ED110 extends Engine{
        public ED110(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.DIESEL;
            this.power = "110kW";
            this.possibleEngineType = Set.of(EngineType.DIESEL.name());
            this.possiblePower = Set.of("110kW");
            addProperty("type", this::getEngineType , this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    public static class ED180 extends Engine{
        public ED180(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.DIESEL;
            this.power = "180kW";
            this.possibleEngineType = Set.of(EngineType.DIESEL.name());
            this.possiblePower = Set.of("180kW");
            addProperty("type", this::getEngineType , this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    public static class EH120 extends Engine{
        public EH120(PartTypeImpl pti) {
            super(pti);
            this.engineType = EngineType.GASOLINE_ELECTRIC_HYBRID;
            this.power = "120kW";
            this.possibleEngineType = Set.of(EngineType.GASOLINE_ELECTRIC_HYBRID.name());
            this.possiblePower = Set.of("120kW");
            addProperty("type", this::getEngineType , this::setEngineType, possibleEngineType);
            addProperty(POWER_PROPERTY, this::getPower, this::setPower, possiblePower);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Engine engine = (Engine) o;
        return engineType == engine.engineType && Objects.equals(power, engine.power) && Objects.equals(possibleEngineType, engine.possibleEngineType) && Objects.equals(possiblePower, engine.possiblePower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), engineType, power, possibleEngineType, possiblePower);
    }
}
