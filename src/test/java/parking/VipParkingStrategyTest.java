package parking;

import mocking.MessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)

public class VipParkingStrategyTest {
    private static final String PARKING_LOT_NAME_A = "A";
    private static final String PARKING_LOT_NAME_B = "B";

    public static String NO_PARKING_LOT = "No Parking Lot";
    private static final String CAR_NAME_OL1234 = "OL1234";

    @Mock
    CarDao carDao;

    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {
	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
        Car car = spy(new Car(CAR_NAME_OL1234));
        ParkingLot parkingLot = spy(new ParkingLot(PARKING_LOT_NAME_A, 1));
        List<ParkingLot> parkingLots = Collections.singletonList(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());

        Receipt receipt = vipParkingStrategy.park(parkingLots, car);
        assertEquals(PARKING_LOT_NAME_A, receipt.getParkingLotName());
        verify(parkingLot, times(1)).getName();
        verify(car, times(1)).getName();
    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {
        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        Car car = spy(new Car(CAR_NAME_OL1234));
        ParkingLot parkingLot = spy(new ParkingLot(PARKING_LOT_NAME_A, 0));
        List<ParkingLot> parkingLots = Collections.singletonList(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());

        Receipt receipt = vipParkingStrategy.park(parkingLots, car);
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());
        verify(parkingLot, times(0)).getName();
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(true);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar(CAR_NAME_OL1234));
        assertTrue(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(false);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar(CAR_NAME_OL1234));
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(false);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar(CAR_NAME_OL1234));
        assertFalse(isVip);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        when(carDao.isVip(any())).thenReturn(false);
        Boolean isVip = vipParkingStrategy.isAllowOverPark(createMockCar(CAR_NAME_OL1234));
        assertFalse(isVip);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
