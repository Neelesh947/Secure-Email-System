package in.neelesh.Email.Service;

import java.util.Map;

import in.neelesh.Email.Entity.User;

public interface AuthService {

	User findByEmail(String email);
	
	User registerUser(Map<String, String> req);

}
