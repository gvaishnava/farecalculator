package tigercard.farecalculation;

import org.junit.Test;

import junit.framework.TestCase;
import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.executor.hourlyfare.HourlyFareExecutor;
import tigercard.farecalculation.executor.hourlyfare.PeakHourFareExecutor;
import tigercard.farecalculation.model.Zone;

public class TestHourlyFare extends TestCase{
	
	/**
	 *  peak hours fare calculation for journey between zone1 to zone2
	 */
	@Test
	public void testPeakHourFareFromZone1toZone2() {
		
		Zone zone1to1 = new Zone(ZoneType.ZONE_1, ZoneType.ZONE_1);
		
		HourlyFareExecutor hourlyfareExecutor = new PeakHourFareExecutor();
		
		double peakhourfareforzone1to1 = hourlyfareExecutor.getHourlyFare(zone1to1);
		
		assertEquals(peakhourfareforzone1to1, 30.0);
	}

}
