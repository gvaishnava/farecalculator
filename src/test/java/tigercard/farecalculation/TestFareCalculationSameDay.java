package tigercard.farecalculation;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tigercard.farecalculation.model.CommuterJourney;
import tigercard.farecalculation.model.Zone;
import tigercard.farecalculation.service.FareCalucation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestFareCalculationSameDay {
	@Autowired
	FareCalucation fareCalculationSameDay;
	
	@Test
	public void testFareCalcuationSameDay() {
		
		// 04/01/2021 10.20 2 1
		CommuterJourney journey = new CommuterJourney("04/01/2021", "10.20", new Zone(2, 1));
		try {
			fareCalculationSameDay.caculateFare(journey);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 04/01/2021 10.45 1 1
		journey = new CommuterJourney("04/01/2021", "10.45", new Zone(1, 1));
		try {
			fareCalculationSameDay.caculateFare(journey);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 04/01/2021 16.15 1 1
		journey = new CommuterJourney("04/01/2021", "16.15", new Zone(1, 1));
		try {
			fareCalculationSameDay.caculateFare(journey);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 04/01/2021 18.15 1 1
		journey = new CommuterJourney("04/01/2021", "18.15", new Zone(1, 1));
		try {
			fareCalculationSameDay.caculateFare(journey);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 04/01/2021 19.00 1 2
		journey = new CommuterJourney("04/01/2021", "19.00", new Zone(1, 2));
		try {
			fareCalculationSameDay.caculateFare(journey);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("TOTAL FARE: "+fareCalculationSameDay.getTotalFare());
		
		Assert.assertEquals(120.0,fareCalculationSameDay.getTotalFare(),0.001);
	}
	
	

}
