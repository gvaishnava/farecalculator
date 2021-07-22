package tigercard.farecalculation.executor.faretype;

import java.util.Date;

import tigercard.farecalculation.model.Zone;

/**
 * Common interface among different types of fare type calculation
 */
public interface FareTypeExecutor {

	/**
	 * Method to calculate fare type for input zone
	 * 
	 * @param zone
	 * @return fare type [peek hour or off-peak hours] for input zone
	 */
	int getFareType(Date datetime, Zone zone);
}
