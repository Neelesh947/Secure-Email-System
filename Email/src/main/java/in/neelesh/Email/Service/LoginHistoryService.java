package in.neelesh.Email.Service;

import java.util.List;

import in.neelesh.Email.Entity.LoginHistory;
import in.neelesh.Email.Entity.User;

public interface LoginHistoryService {

	public void log(User user, String ipAddress, boolean success);
	
	public List<LoginHistory> getUserHistory(User user);
}
