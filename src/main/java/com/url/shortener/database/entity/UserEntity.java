package com.url.shortener.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    @Builder.Default
    private ServerRole role = ServerRole.USER;
    @Builder.Default
    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;
    @Builder.Default
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;
    @Builder.Default
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;
    @Builder.Default
    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public enum ServerRole {
        USER,
        ADMIN
    }
}
