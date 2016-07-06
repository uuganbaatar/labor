package mn.odi.labor.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mn.odi.labor.entities.admin.AjiliinBairHurungu;
import mn.odi.labor.entities.admin.CompanyHelber;
import mn.odi.labor.entities.admin.CompanyStatus;
import mn.odi.labor.entities.admin.CompanyTrend;
import mn.odi.labor.entities.admin.EconomicCategory;
import mn.odi.labor.entities.admin.GeneralType;
import mn.odi.labor.entities.admin.LavlahGarsan;
import mn.odi.labor.entities.admin.PropertyType;
import mn.odi.labor.entities.common.AccessLog;
import mn.odi.labor.entities.common.BaseObject;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.SumDuureg;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.entities.labor.Employee;
import mn.odi.labor.entities.labor.Job;
import mn.odi.labor.entities.labor.Report;
import mn.odi.labor.entities.labor.ReportDetail;
import mn.odi.labor.entities.labor.ReportStatus;
import mn.odi.labor.enums.AimagNiislelEnum;
import mn.odi.labor.enums.GenderEnum;
import mn.odi.labor.enums.JobTypeEnum;
import mn.odi.labor.enums.ReportDetailType;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

public interface SccDAO {

	public Date getCurrentDate();

	public void saveObject(BaseObject obj);

	public void updateObject(BaseObject obj);

	// public void saveOrUpdateObject(BaseObject obj);

	public void saveObject(Object obj);

	public void updateObject(Object obj);

	public void saveOrUpdateObject(Object obj);

	public void mergeObject(Object obj);

	@CommitAfter
	public void deleteObject(Object obj);

	public void deleteBaseObject(BaseObject obj);

	public Object getObject(Class<?> clss, Long id);

	/**
	 * @param clss
	 *            - Entity.
	 * @param id
	 *            - Tuhain entity-ees duudah bichlegin ID.
	 * @param fetchProperty
	 *            - JOIN hiih talbarin ner.
	 * @return Object.
	 */

	public Object getObject(Class<?> clss, Long id, String fetchProperty);

	/**
	 * @param clss
	 *            - Entity.
	 * @param id
	 *            - Tuhain entity-ees duudah bichlegin ID.
	 * @param fetchProperties
	 *            JOIN hiih talbaruudin ner.
	 * @return Object.
	 */
	public Object getObject(Class<?> clss, Long id, List<String> fetchProperties);

	public User getUserByUsername(String username);

	public List<User> getUserList();

	public List<Job> getJobList();

	public List<GeneralType> getGeneralTypeList();

	public List<PropertyType> getPropertyTypeList();

	public List<EconomicCategory> getEconomicCategoryList();

	public List<CompanyTrend> getCompanyTrendList();

	public List<AjiliinBairHurungu> getLavlahHurunguList();

	public List<CompanyHelber> getLavlahHelberList();

	public List<CompanyStatus> getLavlahStatusList();

	public List<LavlahGarsan> getLavlahEmpGarsanList();

	public List<Employee> getEmpList();

	public List<AjiliinBairHurungu> getFundingSourceList();

	public List<Organization> getOrgList();

	public List<Employee> getEmpFilter(Employee emp);

	public List<Report> getReportList();

	public ReportStatus getReportStatusList(Report report, Integer year, Integer month, Organization orgId);

	public List<ReportDetail> getReportDetailList();

	public ReportDetail getReportDetailListWithParameter(GeneralType generalType, ReportDetailType detailType,
			JobTypeEnum jobType, Integer year, Integer month);

	public Long getReportDetailAddRemove(GeneralType generalType, ReportDetailType detailType, Integer year,
			Integer month);

	public Integer getAllJobs(Long org, Long sum);

	public Integer getNewJobs(Long org, Long sum);

	public Integer getAllEmployees(Long org, Long sum);

	public boolean isJobExists(Job job);

	public List<AccessLog> getAccessLogs();

	public List<User> getUserListSearch(String ln, String fn, String mail, Date d1, Date d2, Boolean b);

	public List<Employee> getEmpListSearch(Organization org, Job job, String emp, String sur, String phone);

	public List<GeneralType> getGeneralTypeListSearch(String name, Date d1, Date d2, Boolean b);

	public List<PropertyType> getPropertyTypeListSearch(String name, Date d1, Date d2, Boolean b);

	public List<EconomicCategory> getEconomicCategoryListSearch(String name, Date d1, Date d2, Boolean b);

	public List<CompanyTrend> getCompanyTrendListSearch(String name, Date d1, Date d2, Boolean b);

	public List<AjiliinBairHurungu> getLavlahHurunguListSearch(String name, Date d1, Date d2, Boolean b);

	public List<AccessLog> getAccessLogsSearch(String lname, String fname, Date d1, Date d2);

	public List<User> getUserListByLastName(String name);

	public List<User> getUserListByFirstName(String name);

	public List<CompanyHelber> getLavlahHelberListSearch(String name, Date d1, Date d2, Boolean b);

	public List<CompanyStatus> getLavlahStatusListSearch(String name, Date d1, Date d2, Boolean b);

	public List<LavlahGarsan> getLavlahEmpGarsanListSearch(String name, Date d1, Date d2, Boolean b);

	public List<Organization> getOrgListSearch(String name, Date d1, Date d2, Boolean b);

	public List<BigDecimal> getInfoBar();

	public GeneralType getGeneralTypeByName(String name);

	public PropertyType getPropertyTypeByName(String name);

	public EconomicCategory getEconomicCategoryByName(String name);

	public CompanyTrend getCompanyTrendByName(String name);

	public AjiliinBairHurungu getHurunguByName(String name);

	public CompanyHelber getHelberByName(String name);

	public CompanyStatus getStatusByName(String name);

	public LavlahGarsan getGarsanByName(String name);

	public Integer getTotalJan(int year, Long org, Long sum);

	public Integer getTotalFemaleJan(int year, Long org, Long sum);

	public List<SumDuureg> getSumDuureg();

	public SumDuureg getSumDuuregByName(String name, AimagNiislelEnum aimagId);

	public Integer getTotalFeb(int year, Long org, Long sum);

	public Integer getTotalFemaleFeb(int year, Long org, Long sum);

	public Integer getTotalMar(int year, Long org, Long sum);

	public Integer getTotalFemaleMar(int year, Long org, Long sum);

	public Integer getTotalApr(int year, Long org, Long sum);

	public Integer getTotalFemaleApr(int year, Long org, Long sum);

	public Integer getTotalMay(int year, Long org, Long sum);

	public Integer getTotalFemaleMay(int year, Long org, Long sum);

	public Integer getTotalJun(int year, Long org, Long sum);

	public Integer getTotalFemaleJun(int year, Long org, Long sum);

	public Integer getTotalJul(int year, Long org, Long sum);

	public Integer getTotalFemaleJul(int year, Long org, Long sum);

	public Integer getTotalAug(int year, Long org, Long sum);

	public Integer getTotalFemaleAug(int year, Long org, Long sum);

	public Integer getTotalSep(int year, Long org, Long sum);

	public Integer getTotalFemaleSep(int year, Long org, Long sum);

	public Integer getTotalOct(int year, Long org, Long sum);

	public Integer getTotalFemaleOct(int year, Long org, Long sum);

	public Integer getTotalNov(int year, Long org, Long sum);

	public Integer getTotalFemaleNov(int year, Long org, Long sum);

	public Integer getTotalDec(int year, Long org, Long sum);

	public Integer getTotalFemaleDec(int year, Long org, Long sum);

	public List<SumDuureg> getSumDuuregSearch(String name, AimagNiislelEnum aimagId);

	public void saveOrUpdate(BaseObject object, boolean hasMessage);

	public List<Organization> getOrgListByAssoc();

	public Organization getOrgByNameDuureg(String name, SumDuureg sumDuureg);

	public List<Job> getJobListByOrg(Organization org);

	public List<Job> getJobSearch(GeneralType generalType, String name, boolean check, Date d1, Date d2,
			AjiliinBairHurungu fundSource, JobTypeEnum type, Organization org);

	public Integer getHasagdsanJobs(Long org, Long sum);

	public Integer getAllJobsSum(AimagNiislelEnum aimag_id, Date firstdate, Date lastdate);

	public Integer getEZJobsSum(AimagNiislelEnum aimag_id, CompanyTrend companyTrendId, Date firstdate, Date lastdate);

	public boolean checkEmpReg(String regNum);

	public Employee getEmpByReg(String regNum);

	public Integer getTotalImpJul(int year, Long org, Long sum);

	public Integer getTotalImpJan(int year, Long org, Long sum);

	public Integer getTotalImpFeb(int year, Long org, Long sum);

	public Integer getTotalImpMar(int year, Long org, Long sum);

	public Integer getTotalImpApr(int year, Long org, Long sum);

	public Integer getTotalImpMay(int year, Long org, Long sum);

	public Integer getTotalImpJun(int year, Long org, Long sum);

	public Integer getTotalImpAug(int year, Long org, Long sum);

	public Integer getTotalImpSep(int year, Long org, Long sum);

	public Integer getTotalImpOct(int year, Long org, Long sum);

	public Integer getTotalImpNov(int year, Long org, Long sum);

	public Integer getTotalImpDec(int year, Long org, Long sum);

	public Integer getRestJobsSum(AimagNiislelEnum aimag_id, GeneralType generalTypeId, CompanyHelber helberId,
			PropertyType propertyId, Date firstdate, Date lastdate);

	public Integer getJobsSumGender(AimagNiislelEnum aimag_id, GeneralType generalTypeId, GenderEnum gender,
			Date firstdate, Date lastdate);

	public Integer getJobsTrendSumGender(AimagNiislelEnum aimag_id, CompanyTrend generalTypeId, GenderEnum gender,
			Date firstdate, Date lastdate);
	
	public List<Organization> getOrgListBySumId(SumDuureg sum);
	
	public List<Job> getJobSearchSum(GeneralType generalType, String name,
			boolean check, Date d1, Date d2, AjiliinBairHurungu fundSource,
			JobTypeEnum type, SumDuureg sum);
	
	public List<Organization> getOrgListById(Long id);
	
	public List<Organization> getOrgListBySum(SumDuureg sum);

}
