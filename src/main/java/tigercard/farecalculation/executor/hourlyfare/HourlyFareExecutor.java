package tigercard.farecalculation.executor.hourlyfare;

import tigercard.farecalculation.model.Zone;

/**
 * Common interface to get hourly fare for different type of fare type
 *
 */
public interface HourlyFareExecutor {

	/**
	 * Method to calculate hourly fare for different type of fare type
	 * 
	 * @param zone
	 * @return fare for fare type
	 */
	double getHourlyFare(Zone zone);

}
