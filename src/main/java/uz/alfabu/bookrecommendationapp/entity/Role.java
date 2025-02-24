package uz.alfabu.bookrecommendationapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.alfabu.bookrecommendationapp.entity.abstractentity.AbsDateTimeEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Role extends AbsDateTimeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissions;

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Role role && Objects.equals(this.name, role.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
