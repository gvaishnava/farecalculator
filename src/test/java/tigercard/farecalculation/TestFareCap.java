package tigercard.farecalculation;

import org.junit.Test;

import junit.framework.TestCase;
import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.executor.farecap.DailyFareCapExecutor;
import tigercard.farecalculation.executor.farecap.FareCapExecutor;
import tigercard.farecalculation.model.Zone;

public class TestFareCap extends TestCase {


	/**
	 * Identify daily fare cap for journey done between zone1 to zone1
	 */
	@Test
	public void testDailyFareCapFromZone1toZone2() {

		Zone zone1to1 = new Zone(ZoneType.ZONE_1, ZoneType.ZONE_1);
		
		FareCapExecutor fareCapExecutor = new DailyFareCapExecutor();
		
		double dailycapforzone1to1 = fareCapExecutor.getFareCap(zone1to1);
		
		assertEquals(dailycapforzone1to1, 100.0);
	}
}
