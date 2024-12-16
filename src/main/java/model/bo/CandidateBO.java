package model.bo;

import java.sql.SQLException;

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
}