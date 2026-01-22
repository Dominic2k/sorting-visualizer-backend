package com.sortingvisualizer.security;

import com.sortingvisualizer.model.AuthProvider;
import com.sortingvisualizer.model.User;
import com.sortingvisualizer.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * Handler được gọi sau khi user đăng nhập Google thành công.
 * Tạo hoặc cập nhật user trong database, sau đó tạo JWT và redirect về frontend.
 */
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    
    @Value("${app.frontend-url:http://localhost:5173}")
    private String frontendUrl;
    
    public OAuth2AuthenticationSuccessHandler(
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }
    
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        
        // Lấy thông tin từ Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String pictureUrl = oAuth2User.getAttribute("picture");
        String googleId = oAuth2User.getAttribute("sub");
        
        // Tìm hoặc tạo user mới
        User user = userRepository.findByEmail(email)
            .map(existingUser -> updateExistingUser(existingUser, name, pictureUrl))
            .orElseGet(() -> createNewUser(email, name, pictureUrl, googleId));
        
        // Tạo JWT token
        String token = jwtTokenProvider.generateToken(user.getEmail());
        
        // Redirect về frontend với token
        String targetUrl = UriComponentsBuilder.fromUriString(frontendUrl)
            .path("/oauth2/callback")
            .queryParam("token", token)
            .build()
            .toUriString();
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    
    private User updateExistingUser(User user, String name, String pictureUrl) {
        // Cập nhật thông tin nếu đăng nhập bằng Google
        if (user.getProvider() == AuthProvider.GOOGLE) {
            user.setDisplayName(name);
            user.setAvatarUrl(pictureUrl);
            return userRepository.save(user);
        }
        // Nếu user đã đăng ký bằng email/password trước đó, chỉ cập nhật avatar
        user.setAvatarUrl(pictureUrl);
        return userRepository.save(user);
    }
    
    private User createNewUser(String email, String name, String pictureUrl, String googleId) {
        User newUser = User.builder()
            .email(email)
            .displayName(name)
            .avatarUrl(pictureUrl)
            .provider(AuthProvider.GOOGLE)
            .providerId(googleId)
            .build();
        return userRepository.save(newUser);
    }
}
