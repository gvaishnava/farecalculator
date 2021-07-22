package tigercard.farecalculation.executor.hourlyfare;

import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.model.Zone;

/**
 * Logic to calculate off-peak hours fare
 *
 */
public class OffPeakHoursFareExecutor implements HourlyFareExecutor {

	/**
	 * Calculate fare for off-peak hours for input zone
	 * 
	 * @param zone - journey information between zone
	 * @return off-peak hours fare
	 */
	public double getHourlyFare(Zone zone) {
		if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 25.0;
		} else if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 30.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 30.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 20.0;
		}
		return 0;
	}

}
