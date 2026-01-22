package com.sortingvisualizer.model;

/**
 * Enum representing the authentication provider used by a user.
 * LOCAL: Traditional email/password registration
 * GOOGLE: OAuth2 login via Google
 */
public enum AuthProvider {
    LOCAL,    // Đăng ký bằng email/password
    GOOGLE    // Đăng nhập bằng Google OAuth2
}
