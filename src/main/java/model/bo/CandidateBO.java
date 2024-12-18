package model.bo;

import java.sql.SQLException;
import java.util.List;

import dto.CandidateInAdmin;
import model.bean.Candidate;
import model.dao.CandidateDAO;

public class CandidateBO {
    private static CandidateBO instance;
    private CandidateDAO candidateDAO = CandidateDAO.getInstance();

    private CandidateBO() {	}

    public static CandidateBO getInstance() {
        if (instance == null) {
        	synchronized(CandidateBO.class) {
        		instance = new CandidateBO();
        	}
        }
        return instance;
    }

    public boolean createCandidate(Candidate candidate) throws SQLException {
        return candidateDAO.createCandidate(candidate);
    }
    
	public Candidate findCandidateByAccountId(String accountId) {
		return candidateDAO.findCandidateByAccountId(accountId);
	}
	
	public boolean updateCandidate(Candidate candidate) throws SQLException {
		return candidateDAO.updateCandidate(candidate);
	}
	
	//get profie by id
	public Candidate getCandidateProfile(String id) throws SQLException{
		return CandidateDAO.getInstance().getCandidateProfile(id);
	}
	
	//update profile
	public boolean updateCandidateProfile(String id, String name, String email) throws SQLException {
		return CandidateDAO.getInstance().updateCandidateProfile(id, name, email);
	}
	
	public boolean deleteCandidateById(String id) throws SQLException{
		return CandidateDAO.getInstance().deleteCandidateById(id);
	}
    
	public List<CandidateInAdmin> getCandidates(int page, int recordsPerPage) throws SQLException{
		int start = (page - 1) * recordsPerPage;
		return candidateDAO.getCandidates(start, recordsPerPage);
	}
	
	public int getTotalPages(int recordsPerPage) throws SQLException{
		return (int) Math.ceil((double) candidateDAO.getTotalRecords() / recordsPerPage);
	}
	
	public List<CandidateInAdmin> searchCandidates(int page, int recordsPerPage, String searchText) throws SQLException{
		int start = (page - 1) * recordsPerPage;
		return candidateDAO.searchCandidates(start, recordsPerPage, searchText);
	}
	
	public int getSearchTotalPages(int recordsPerPage, String searchText) throws SQLException{
		return (int) Math.ceil((double) candidateDAO.getSearchTotalRecords(searchText) / recordsPerPage);
	}
}