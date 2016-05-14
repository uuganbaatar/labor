package mn.odi.labor.util;


public class RateCalculationUtil {

	public static Double siteIncentive(Integer baseRate) {
		Double siteIntencive = (double) 0;
		siteIntencive = (double) (baseRate * Constants.D9 / Constants.THOUSAND);
		return siteIntencive;
	}

	public static Double vacationPay(Integer baseRate) {
		Double vacation = (double) 0;
		vacation = (double) (baseRate + siteIncentive(baseRate));
		vacation = vacation * Constants.D8 / Constants.THOUSAND;
		return vacation;
	}

	public static Double vacationPayUB(Integer baseRate) {
		Double vacation = (double) 0;
		vacation = (double) (baseRate);
		vacation = vacation * Constants.D8 / Constants.THOUSAND;
		return vacation;
	}

	public static Double shiCompany(Integer baseRate) {
		Double shi = (double) 0;
		shi = (double) (baseRate + siteIncentive(baseRate) + vacationPay(baseRate));
		shi = shi * Constants.D6 / Constants.THOUSAND;
		return shi;
	}

	public static Double shiCompanyUB(Integer baseRate) {
		Double shi = (double) 0;
		shi = (double) (baseRate + vacationPayUB(baseRate));
		shi = shi * Constants.D19 / Constants.THOUSAND;
		return shi;
	}

	public static Double serviceCharge(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D7 / Constants.THOUSAND);
		return service;
	}

	public static Double totalInclusiveOverTimeRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D5 * Constants.D2 * Constants.D3);
		return service;
	}

	public static Double totalInclusiveNightRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D10 + siteIncentive(baseRate));
		service = (double) (service * Constants.D2 * Constants.D3);
		service = service + serviceCharge(baseRate);
		return service;
	}

	public static Double totalInclusiveNightRateUB(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D10);
		service = (double) (service * Constants.D2 * Constants.D3);
		service = service + serviceCharge(baseRate);
		return service;
	}

	public static Double totalInclusiveHolidayRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D4 * Constants.D3);
		return service;
	}

	public static Double eli_ot(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (((baseRate * Constants.D11 * Constants.D12
				* Constants.D13 * Constants.D14) + Constants.D15) / Constants.D6);
		return service;
	}

	public static Double eli_office(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (((baseRate * Constants.D11 * Constants.D12
				* Constants.D13 * Constants.D16) + Constants.D15) / Constants.D6);
		return service;

	}

	public static Double eli_ub(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (((baseRate * 264 * 3 * 8 * Constants.D16) + 20000) / Constants.D6);
		return service;

	}

}
