package tigercard.farecalculation.executor.farecap;

import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.model.Zone;

/**
 * Logic to calculate weekly fare cap
 *
 */
public class WeeklyFareCapExecutor implements FareCapExecutor {

	/**
	 * Calculate fare for weekly fare cap input zone
	 * 
	 * @param zone - journey information between zone
	 * @return weekly fare cap
	 */
	public double getFareCap(Zone zone) {

		if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 500.0;
		} else if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 600.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 600.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 400.0;
		}
		return 0;

	}
}
