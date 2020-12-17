package part;

import impl.PartImpl;
import impl.PartTypeImpl;

import java.util.Objects;
import java.util.Set;

public abstract class Transmission extends  PartImpl{

    private enum TransmissionType {
        MANUAL,
        AUTOMATIC,
        SEQUENTIAL,
        CONVERTER
    }

    private enum Speed{
        GEAR_5,
        GEAR_6,
        GEAR_7_WHEELS_4,
        KWMAX_120
    }

    protected TransmissionType transmissionType;
    protected Speed speed;
    protected Set<String> possibleTransmissionType;
    protected Set<String> possibleSpeed ;

    private Transmission(PartTypeImpl pti) {
        super(pti);
    }

    protected String getTransmissionType() {
        return transmissionType.name();
    }

    protected void setTransmissionType(String value) {
        assert getAvailablePropertyValues("type").contains(value);
        this.transmissionType = TransmissionType.valueOf(value);
    }

    protected String getSpeed() {
        return speed.name();
    }

    protected void setSpeed(String value) {
        assert getAvailablePropertyValues("speed").contains(value);
        this.speed = Speed.valueOf(value);
    }

    public static class TM5 extends Transmission{
        public TM5(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.MANUAL;
            this.speed = Speed.GEAR_5;
            this.possibleTransmissionType = Set.of(TransmissionType.MANUAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_5.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    public static class TM6 extends Transmission{
        public TM6(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.MANUAL;
            this.speed = Speed.GEAR_6;
            this.possibleTransmissionType = Set.of(TransmissionType.MANUAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_6.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    public static class TA5 extends Transmission{
        public TA5(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.AUTOMATIC;
            this.speed = Speed.GEAR_5;
            this.possibleTransmissionType = Set.of(TransmissionType.AUTOMATIC.name());
            this.possibleSpeed = Set.of(Speed.GEAR_5.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    public static class TS6 extends Transmission{
        public TS6(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.SEQUENTIAL;
            this.speed = Speed.GEAR_6;
            this.possibleTransmissionType = Set.of(TransmissionType.SEQUENTIAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_6.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    public static class TSF7 extends Transmission{
        public TSF7(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.SEQUENTIAL;
            this.speed = Speed.GEAR_7_WHEELS_4;
            this.possibleTransmissionType = Set.of(TransmissionType.SEQUENTIAL.name());
            this.possibleSpeed = Set.of(Speed.GEAR_7_WHEELS_4.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
        }
    }

    public static class TC120 extends Transmission{
        public TC120(PartTypeImpl pti) {
            super(pti);
            this.transmissionType = TransmissionType.CONVERTER;
            this.speed = Speed.KWMAX_120;
            this.possibleTransmissionType = Set.of(TransmissionType.CONVERTER.name());
            this.possibleSpeed = Set.of(Speed.KWMAX_120.name());
            addProperty("type", this::getTransmissionType, this::setTransmissionType, possibleTransmissionType);
            addProperty("speed", this::getSpeed, this::setSpeed, possibleSpeed);
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
