package tigercard.farecalculation;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tigercard.farecalculation.executor.faretype.repo.HoursForFareTypeRepo;
import tigercard.farecalculation.model.HoursForFareType;
import tigercard.farecalculation.constant.FareType;
import tigercard.farecalculation.constant.ZoneType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FareTypeRepoTest {

	@Autowired
	private HoursForFareTypeRepo faretyperepo;

	@Test
	public void testFareTypeRepo() {

		// FareType hourly timings total entry
		Assert.assertEquals(21, faretyperepo.count());
		
		// Get fate type for journey on Sunday at 19:00 hours peak hours
		List<HoursForFareType> faretypeSunday_Hours_19_00 = faretyperepo
				.findByDaysAndStarttimeLessThanEqualAndEndtimeGreaterThanEqualAndFromzoneAndTozone("SUNDAY", 19.0, 19.0,
						ZoneType.ZONE_ANY, ZoneType.ZONE_ANY);
		
		// Should return unique record as there is only one entry for Sunday on journey time
		Assert.assertEquals(1, faretypeSunday_Hours_19_00.size());
		Assert.assertEquals(FareType.PEAK_HOURS, faretypeSunday_Hours_19_00.get(0).getPeakhourtype());

		// Get fate type for journey on Monday at 19:00 hours off peak hours zone2 to zone1
		List<HoursForFareType> faretypeMonday_Hours_19_00_Zone2To1 = faretyperepo
				.findByDaysAndStarttimeLessThanEqualAndEndtimeGreaterThanEqualAndFromzoneAndTozone("MONDAY", 19.0, 19.0,
						ZoneType.ZONE_2, ZoneType.ZONE_1);
		
		// Should return unique record as there is only one entry for Monday on journey time and zone
		Assert.assertEquals(1, faretypeMonday_Hours_19_00_Zone2To1.size());
		Assert.assertEquals(FareType.OFF_PEAK_HOURS, faretypeMonday_Hours_19_00_Zone2To1.get(0).getPeakhourtype());

	}

}
