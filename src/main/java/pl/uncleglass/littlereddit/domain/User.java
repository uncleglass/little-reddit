package pl.uncleglass.littlereddit.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.uncleglass.littlereddit.domain.validators.PasswordsMatch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@PasswordsMatch
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String email;

    @NonNull
    private String password;

    @NonNull
    @Column(nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @NonNull
    @NotBlank(message = "You must enter First Name.")
    private String firstName;

    @NonNull
    @NotBlank(message = "You must enter Last Name.")
    private String lastName;

    @Transient
    @Setter(AccessLevel.NONE)
    private String fullName;

    @Transient
    @NotBlank(message = "Please enter Password Confirmation")
    private String confirmPassword;

    private String activationCode;

    @NonNull
    @NotBlank(message = "Please enter alias.")
    @Column(nullable = false, unique = true)
    private String alias;

    public String getFullName(){
        return firstName + " " + lastName;
    }


    public void addRole(Role userRole) {
        roles.add(userRole);
    }

    public void addRoles(HashSet<Role> roles) {
        roles.forEach(this::addRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
