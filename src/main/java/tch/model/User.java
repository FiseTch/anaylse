package tch.model;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class User {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public void setAuthor(ArrayList<String> arrayList) {
		
	}
}