import api.*;
import impl.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import part.Engine;
import part.Exterior;
import part.Interior;
import part.Transmission;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PartsTest {

    @DisplayName("Get Engine Properties Test")
    @Test
    void getEnginePropertiesTest(){
        Category engineCategory = new CategoryImpl("engine");
        PartTypeImpl EG100 = new PartTypeImpl("EG100", Engine.EG100.class,engineCategory);

        PartImpl EG100Instance = EG100.newInstance();

        assertEquals(EG100Instance.getProperty(Engine.TYPE_PROPERTY).get(),Engine.EngineType.GASOLINE.name());
        assertEquals(EG100Instance.getProperty(Engine.POWER_PROPERTY).get(), "100kW");
    }

    @DisplayName("Get Exterior Properties Test")
    @Test
    void getExteriorPropertiesTest(){
        Category exteriorCategory = new CategoryImpl("exterior");
        PartTypeImpl XC = new PartTypeImpl("XC", Exterior.XC.class,exteriorCategory);

        PartImpl XCInstance = XC.newInstance();

        assertEquals(XCInstance.getProperty(Exterior.PAINT_PROPERTY).get(),Exterior.PaintColor.CLASSIC.name());
    }

    @DisplayName("Get Interior Properties Test")
    @Test
    void getInteriorPropertiesTest(){
        Category interiorCategory = new CategoryImpl("interior");
        PartTypeImpl IN = new PartTypeImpl("IN", Interior.IN.class,interiorCategory);

        PartImpl INInstance = IN.newInstance();

        assertEquals(INInstance.getProperty(Interior.STYLE_PROPERTY).get(),Interior.InteriorStyle.STANDARD.name());
    }

    @DisplayName("Get Transmission Properties Test")
    @Test
    void getTransmissionPropertiesTest(){
        Category transmissionCategory = new CategoryImpl("transmission");
        PartTypeImpl TA5 = new PartTypeImpl("TA5", Transmission.TA5.class,transmissionCategory);

        PartImpl TA5Instance = TA5.newInstance();

        assertEquals(TA5Instance.getProperty(Transmission.TYPE_PROPERTY).get(),Transmission.TransmissionType.AUTOMATIC.name());
        assertEquals(TA5Instance.getProperty(Transmission.SPEED_PROPERTY).get(), Transmission.Speed.GEAR_5.name());
    }
}
