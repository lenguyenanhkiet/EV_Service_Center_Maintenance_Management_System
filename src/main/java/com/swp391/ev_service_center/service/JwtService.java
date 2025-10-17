package com.swp391.ev_service_center.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    /**
    * Trích xuất email từ JwtToken
     * @param token chuỗi Jwt
     * @return Email
     * */
    String extractUsername(String token);
    /**
     * Tạo ra một JWT token mới cho một người dùng.
     * @param userDetails Đối tượng UserDetails chứa thông tin người dùng.
     * @return Chuỗi Jwt Token
     * */
    String generateToken (UserDetails userDetails);
    /**
     * Kiểm tra xem một JWT token có hợp lệ hay không.
     * Token được coi là hợp lệ nếu nó chưa hết hạn và thuộc về đúng người dùng.
     * @param token Chuỗi JWT.
     * @param userDetails Đối tượng UserDetails để so sánh.
     * @return true nếu token hợp lệ, ngược lại false.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
