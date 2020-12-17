package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

/**
 * Class that represents all the different transmission Parts (for instantiation)
 */
public abstract class Transmission extends  PartImpl{

    /** Constant for property name */
    public static final String SPEED_PROPERTY = "speed";
    /**
     * Enum for all the different transmission types */
    private enum TransmissionType {
        MANUAL,
        AUTOMATIC,
        SEQUENTIAL,
        CONVERTER
    }

    /**Enum for all the different speed types */
    private enum Speed{
        GEAR_5,
        GEAR_6,
        GEAR_7_WHEELS_4,
        KWMAX_120
    }

    /** Transmission type */
    protected TransmissionType transmissionType;
    /** Transmission speed */
    protected Speed speed;
    /** Possible transmission types */
    protected Set<String> possibleTransmissionType;
    /** Possible transmission speeds */
    protected Set<String> possibleSpeed ;

    /**
     * Constructor for the Transmission PartImpl
     * @param pti the linked partType
     */
    private Transmission(PartTypeImpl pti) {
        super(pti);
    }

    /**
     * Get transmission type
     * @return the transmission type
     */
    protected String getTransmissionType() {
        return transmissionType.name();
    }

    /**
     * Set the new transmission type
     * @param value, the new transmission type
     */
    protected void setTransmissionType(String value) {
        assert getAvailablePropertyValues("type").contains(value);
        this.transmissionType = TransmissionType.valueOf(value);
    }

    /**
     * Get the speed of the transmission
     * @return the speed
     */
    protected String getSpeed() {
        return speed.name();
    }

    /**
     * Set the new speed
     * @param value, the new speed
     */
    protected void setSpeed(String value) {
        assert getAvailablePropertyValues(SPEED_PROPERTY).contains(value);
        this.speed = Speed.valueOf(value);
    }

    /**
     * Model for the TM5 instance
     */
    public static class TM5 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TM5(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.MANUAL;
            this.speed = Speed.GEAR_5;
            this.possibleTransmissionType = Set.of(TransmissionType.MANUAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_5.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    /**
     * Model for the TM6 instance
     */
    public static class TM6 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TM6(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.MANUAL;
            this.speed = Speed.GEAR_6;
            this.possibleTransmissionType = Set.of(TransmissionType.MANUAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_6.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    /**
     * Model fot the TA5 instance
     */
    public static class TA5 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TA5(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.AUTOMATIC;
            this.speed = Speed.GEAR_5;
            this.possibleTransmissionType = Set.of(TransmissionType.AUTOMATIC.name());
            this.possibleSpeed = Set.of(Speed.GEAR_5.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    /**
     * Model for the TS6 instance
     */
    public static class TS6 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TS6(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.SEQUENTIAL;
            this.speed = Speed.GEAR_6;
            this.possibleTransmissionType = Set.of(TransmissionType.SEQUENTIAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_6.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    /**
     * Model for the TSF7 instance
     */
    public static class TSF7 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TSF7(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.SEQUENTIAL;
            this.speed = Speed.GEAR_7_WHEELS_4;
            this.possibleTransmissionType = Set.of(TransmissionType.SEQUENTIAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_7_WHEELS_4.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    /**
     * Model for the TC120 instance
     */
    public static class TC120 extends Transmission{
        /**
         * Constuctor for the instance
         * @param pti, the partType linked to this instance
         */
        public TC120(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.CONVERTER;
            this.speed = Speed.KWMAX_120;
            this.possibleTransmissionType = Set.of(TransmissionType.CONVERTER.name());
            this.possibleSpeed = Set.of(Speed.KWMAX_120.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty(SPEED_PROPERTY, this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transmission that = (Transmission) o;
        return transmissionType == that.transmissionType && speed == that.speed && Objects.equals(possibleTransmissionType, that.possibleTransmissionType) && Objects.equals(possibleSpeed, that.possibleSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), transmissionType, speed, possibleTransmissionType, possibleSpeed);
    }
}
