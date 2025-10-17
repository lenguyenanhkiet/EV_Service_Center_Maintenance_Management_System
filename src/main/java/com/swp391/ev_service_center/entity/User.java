package com.swp391.ev_service_center.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name ="users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class )
public class User implements UserDetails {
    // Thông tin tài khoản
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private boolean isActive = true;

    // Thông tin người dùng
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String phone;
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate dob;

    // Nếu là ADMIN, STAFF, TECHNICIAN thì liên kết tới bảng service_center, Null với CUSTOMER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="center_id")
    private ServiceCenter serviceCenter;

    // Thong tin thời gian
    @CreatedDate
    @Column(nullable = false, name = "create_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(nullable = false, name = "update_at")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == null){
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    // RETURN username if present, otherwise fall back to email so authentication and JWT subjects are consistent
    @Override
    public String getUsername() {
        return (this.username != null && !this.username.isBlank()) ? this.username : this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Account expiration not tracked separately; treat as non-expired when active
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Account lock not tracked separately; treat as non-locked when active
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Credentials expiration not tracked separately; treat as non-expired when active
        return this.isActive;
    }

    @Override
    public boolean isEnabled() {
        // Use the isActive flag to indicate whether the account is enabled
        return this.isActive;
    }

    // Enum cho Gender and Role
    public enum Role {
        ADMIN, STAFF, TECHNICIAN, CUSTOMER
    }
    public enum Gender {
        Male, Female
    }
}
