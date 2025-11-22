package in.neelesh.Email.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.neelesh.Email.Entity.LoginHistory;
import in.neelesh.Email.Entity.User;
import in.neelesh.Email.Enum.LoginStatus;
import in.neelesh.Email.Repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService{

	private final LoginHistoryRepository loginHistoryRepository;

	public void log(User user, String ipAddress, boolean success) {
		LoginHistory history = LoginHistory.builder().user(user).ipAddress(ipAddress)
				.status(success ? LoginStatus.SUCCESS : LoginStatus.FAILED).build();
		loginHistoryRepository.save(history);
	}

	public List<LoginHistory> getUserHistory(User user) {
		return loginHistoryRepository.findByUser(user);
	}
}
