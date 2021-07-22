package tigercard.farecalculation.executor.faretype;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tigercard.farecalculation.executor.faretype.service.FareTypeService;
import tigercard.farecalculation.model.Zone;

/**
 * Controller to control service class methods calls
 */
@Controller
public class FareTypeExecutorImpl implements FareTypeExecutor {

	/**
	 * Service class to call back-end or http service method.
	 */
	private FareTypeService faretypeservice;

	/**
	 * inject instance of fare type service call
	 * 
	 * @param faretypeservice
	 */
	@Autowired
	public FareTypeExecutorImpl(FareTypeService faretypeservice) {
		this.faretypeservice = faretypeservice;
	}

	/**
	 * get fare type based on zone and date time
	 * 
	 * @param datetime date of journey
	 * @param zone     journey info between zone
	 * @return fare type [peak hours or off peak hours]
	 */
	public int getFareType(Date datetime, Zone zone) {

		return faretypeservice.checkForFareType(datetime, zone);
	}

}
