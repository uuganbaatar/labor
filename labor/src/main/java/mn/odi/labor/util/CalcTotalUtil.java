package mn.odi.labor.util;

import mn.odi.labor.util.Constants;

public class CalcTotalUtil {

	public static Double nswc;

	public static Double siteIncentive(Integer baseRate) {
		Double siteIntencive = (double) 0;
		siteIntencive = (double) (baseRate * Constants.D9 / Constants.THOUSAND);
		return siteIntencive;
	}

	public static Double vacationPay(Integer baseRate) {
		Double vacation = (double) 0;
		if (baseRate == null)
			baseRate = 0;
		if (getNswc() == null)
			setNswc(Double.valueOf(0));
		vacation = (double) (baseRate + siteIncentive(baseRate) + getNswc());
		vacation = vacation * Constants.D8 / Constants.THOUSAND;
		return vacation;
	}

	public static Double vacationPayUB(Integer baseRate) {
		Double vacationUB = (double) 0;
		vacationUB = (double) (baseRate);
		vacationUB = vacationUB * Constants.D8 / Constants.THOUSAND;
		return vacationUB;
	}

	public static Double shiCompany(Integer baseRate) {
		Double shi = (double) 0;
		double rate = (double) baseRate;
		if (getNswc() == null)
			setNswc(Double.valueOf(0));
		shi = (rate + siteIncentive(baseRate) + vacationPay(baseRate))
				+ getNswc();
		shi = shi * Constants.D23;
		return shi;
	}

	public static Double shiCompanyUB(Integer baseRate) {
		double rate = (double) baseRate;
		Double shiUB = (double) 0;
		if (getNswc() == null)
			setNswc(Double.valueOf(0));
		shiUB = (double) (rate + vacationPayUB(baseRate)) + getNswc();
		shiUB = shiUB * Constants.D22;
		return shiUB;
	}

	public static Double serviceCharge(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D7 / Constants.THOUSAND);
		return service;
	}

	public static Double allIncRateHourly(Integer baseRate) {
		double rate = (double) baseRate;
		Double totalAllIncRateHourly = (double) 0;
		totalAllIncRateHourly = (double) (rate + siteIncentive(baseRate)
				+ vacationPay(baseRate) + shiCompany(baseRate) + serviceCharge(baseRate))
				+ getNswc();
		return totalAllIncRateHourly;
	}

	public static Double allIncRateHourlyUB(Integer baseRate) {
		double rate = (double) baseRate;
		Double allIncRateHourlyUB = (double) 0;
		allIncRateHourlyUB = rate + vacationPayUB(baseRate)
				+ shiCompanyUB(baseRate) + serviceCharge(baseRate);
		return allIncRateHourlyUB;
	}

	public static Double allIncRateHourlySC(Integer baseRate) {
		double rate = (double) baseRate;
		Double allIncRateHourlySC = (double) 0;
		allIncRateHourlySC = (double) (rate + vacationPayUB(baseRate) + shiCompanyUB(baseRate));
		return allIncRateHourlySC;
	}

	public static Integer totalInclusiveOverTimeRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D5 * Constants.D2 * Constants.D3);
		int value_int = (int) Math.round(service);
		return value_int;
	}

	public static Integer totalInclusiveNightRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D10 + siteIncentive(baseRate) + getNswc());
		service = (double) (service * Constants.D2);
		service = (double) service * Constants.D3;
		service = (double) service + serviceCharge(baseRate);
		int value_int = (int) Math.round(service);
		return value_int;
	}

	public static Integer totalInclusiveNightRateUB(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D10);
		service = (double) (service * Constants.D2 * Constants.D3);
		service = service + serviceCharge(baseRate);
		int value_int = (int) Math.round(service);
		return value_int;
	}

	public static Integer totalInclusiveHolidayRate(Integer baseRate) {
		Double service = (double) 0;
		service = (double) (baseRate * Constants.D4 * Constants.D3);
		int value_int = (int) Math.round(service);
		return value_int;
	}

	public static Double getNswc() {
		if (nswc == null)
			return Double.valueOf(0);
		else
			return nswc;
	}

	public static void setNswc(Double nswc) {
		CalcTotalUtil.nswc = nswc;
	}
}