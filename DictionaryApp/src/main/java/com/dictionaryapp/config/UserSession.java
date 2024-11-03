package com.dictionaryapp.config;


import com.dictionaryapp.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
@NoArgsConstructor
public class UserSession {
    private Long id;
    private String username;

    public UserSession(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public boolean isLoggedIn() {
        return id != null;
    }

    public void loqOut() {
        this.id = null;
        this.username = null;
    }


}
