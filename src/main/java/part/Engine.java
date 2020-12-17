package part;

import api.PartType;
import impl.PartImpl;
import impl.PartTypeImpl;
import java.util.Objects;
import java.util.Set;

/**
 * Class that regroup all the templates for the engine instances
 */
public abstract class Engine extends PartImpl{

    /** Constant for property name */
    public static final String POWER_PROPERTY = "power";

    /**Enum for all the different engine type */
    private enum EngineType {
        GASOLINE,
        DIESEL,
        GASOLINE_ELECTRIC_HYBRID
    }
    /** Engine type */
    protected EngineType engineType;
    /** Engine power */
    protected String power;
    /** Possible Engine types */
    protected Set<String> possibleEngineType;
    /** Possible Engine powers */
    protected Set<String> possiblePower ;

    /**
     * Constructor for engine
     * @param type, the original partType
     */
    private Engine(PartType type) {
        super(type);
    }

    /**
     * Get the type of the engine
     * @return the engine Type
     */
    protected String getEngineType() {
        return engineType.name();
    }

    /**
     * Set the new engine type
     * @param value, the new type
     */
    protected void setEngineType(String value) {
        assert getAvailablePropertyValues("engine").contains(value);
        this.engineType = EngineType.valueOf(value);
    }

    /**
     * Get the power of the engine
     * @return the power
     */
    protected String getPower() {
        return power;
    }

    /**
     * Set the new power of the engine
     * @param value, the new value
     */
    protected void setPower(String value) {
        assert getAvailablePropertyValues(POWER_PROPERTY).contains(value);
        this.power = value;
    }

    /**
     * Class that represents an EG100 instance
     */
    public static class EG100 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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
    /**
     * Class that represents an EG133 instance
     */
    public static class EG133 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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

    /**
     * Class that represents an EG120 instance
     */
    public static class EG210 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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

    /**
     * Class that represents an ED110 instance
     */
    public static class ED110 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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
    /**
     * Class that represents an ED180 instance
     */
    public static class ED180 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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

    /**
     * Class that represents an EH120 instance
     */
    public static class EH120 extends Engine{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
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
