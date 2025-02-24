package uz.alfabu.bookrecommendationapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.alfabu.bookrecommendationapp.entity.abstractentity.AbsDateTimeEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbsDateTimeEntity implements UserDetails {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .toList();
    }
}
