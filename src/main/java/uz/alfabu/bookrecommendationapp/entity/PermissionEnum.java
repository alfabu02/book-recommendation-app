package uz.alfabu.bookrecommendationapp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionEnum implements GrantedAuthority {
    GET_ONE_USER,
    GET_ALL_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
