package tigercard.farecalculation.executor.farecap;

import tigercard.farecalculation.constant.ZoneType;
import tigercard.farecalculation.model.Zone;

/**
 * Logic to calculate daily fare cap
 *
 */
public class DailyFareCapExecutor implements FareCapExecutor {

	/**
	 * Calculate fare for daily fare cap input zone
	 * @param zone - journey information between zone
	 * @return daily fare cap
	 */
	public double getFareCap(Zone zone) {

		if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 100.0;
		} else if (zone.getTozone() == ZoneType.ZONE_1 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 120.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_1) {
			return 120.0;
		} else if (zone.getTozone() == ZoneType.ZONE_2 && zone.getFromzone() == ZoneType.ZONE_2) {
			return 80.0;
		}
		return 0;

	}

}
