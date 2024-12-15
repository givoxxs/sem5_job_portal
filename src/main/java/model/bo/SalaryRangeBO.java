package model.bo;

import java.util.List;
import model.bean.SalaryRange;
import model.dao.SalaryRangeDAO;

public class SalaryRangeBO {
	// instance
	public static SalaryRangeBO instance;
	public SalaryRangeDAO salaryRangeDAO = SalaryRangeDAO.getInstance();

	public SalaryRangeBO() {
	}

	public static SalaryRangeBO getInstance() {
		if (instance == null) {
			instance = new SalaryRangeBO();
		}
		return instance;
	}
	
	public List<SalaryRange> getAllAvailableSalaryRanges() {
		return salaryRangeDAO.getAllAvailableSalaryRanges();
	}
}