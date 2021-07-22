package tigercard.farecalculation.executor.hourlyfare;

import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.model.Zone;

/**
 * Logic to calculate peak hours fare
 *
 */
public class PeakHourFareExecutor implements HourlyFareExecutor {

	/**
	 * Calculate fare for peak hours for input zone
	 * 
	 * @param zone - journey information between zone
	 * @return peak hours fare
	 */
	public double getHourlyFare(Zone zone) {
		if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 30.0;
		} else if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 35.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 35.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 25.0;
		}
		return 0;
	}

}
