package parking;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {
    private static final String PARKING_LOT_NAME_A = "A";
    public static String NO_PARKING_LOT = "No Parking Lot";
    private static final String CAR_NAME_OL1234 = "OL1234";

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {
        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(car.getName()).thenReturn(CAR_NAME_OL1234);
        when(parkingLot.getName()).thenReturn(PARKING_LOT_NAME_A);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt spaceReceipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
        assertEquals(CAR_NAME_OL1234, spaceReceipt.getCarName());
        assertEquals(PARKING_LOT_NAME_A, spaceReceipt.getParkingLotName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {
        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(CAR_NAME_OL1234);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt spaceReceipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
        assertEquals(CAR_NAME_OL1234, spaceReceipt.getCarName());
        assertEquals(NO_PARKING_LOT, spaceReceipt.getParkingLotName());
    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {
        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        Car spyCar = spy(new Car(CAR_NAME_OL1234));
        ParkingLot parkingLot = spy(new ParkingLot(PARKING_LOT_NAME_A, 0));
        List<ParkingLot> parkingLots = Collections.singletonList(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        Receipt receipt = inOrderParkingStrategy.park(parkingLots, spyCar);
        assertEquals(ParkingStrategy.NO_PARKING_LOT, receipt.getParkingLotName());
        verify(spyCar, times(1)).getName();
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {
        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {
        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {
        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
    }

}