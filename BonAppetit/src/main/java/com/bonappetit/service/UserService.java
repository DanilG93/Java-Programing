package com.bonappetit.service;

import com.bonappetit.model.DTO.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RecipeService recipeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.bonappetit.model.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    public com.bonappetit.model.entity.User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean validateConfirmPassword(@Valid UserRegisterDTO userRegisterDTO) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
    }

    public void register(@Valid UserRegisterDTO userRegisterDTO, PasswordEncoder passwordEncoder) {
        com.bonappetit.model.entity.User build = new com.bonappetit.model.entity.User().builder()
                .username(userRegisterDTO.getUsername())
                .email(userRegisterDTO.getEmail())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .build();

        userRepository.save(build);
    }


    public boolean findByUsernameAndEqualPassword(String username, String password, PasswordEncoder passwordEncoder) {
        boolean present = userRepository.findByUsername(username).isPresent();
        if (!present) {
            return false;
        } else {
            com.bonappetit.model.entity.User user = userRepository.findByUsername(username).orElse(null);
            return user.getPassword().equals(passwordEncoder.encode(password));
        }
    }

    public void save(com.bonappetit.model.entity.User authorizedUser) {
        userRepository.save(authorizedUser);
    }

    public void removeRecipeFromFavorites(Long recipeId, Authentication authentication) {
        com.bonappetit.model.entity.User authorizedUser = this.findByUsername(authentication.getName());
        authorizedUser.getFavouriteRecipes().remove(recipeService.finnById(recipeId));
        userRepository.save(authorizedUser);
    }
}
