package tigercard.farecalculation.executor.faretype.service;

import java.util.Date;

import tigercard.farecalculation.model.Zone;

/**
 * Service class to perform business logic to calculate fate type
 *
 */
public interface FareTypeService {

	/**
	 * Method for calculate fate type peak hours or off-peak hours
	 * 
	 * @param datatime - time of journey
	 * @param zone     - journey info between zones
	 * @return fare type - peak hours or off-peak hours
	 */
	int checkForFareType(Date datatime, Zone zone);

}
