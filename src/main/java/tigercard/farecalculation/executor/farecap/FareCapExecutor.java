package tigercard.farecalculation.executor.farecap;

import tigercard.farecalculation.model.Zone;

/**
 * Common interface among different type of fare cap calculation
 *
 */
public interface FareCapExecutor {

	/**
	 * Method to calculate fare cap for input zone
	 * 
	 * @param zone
	 * @return fare cap for input zone
	 */
	public double getFareCap(Zone zone);
}
