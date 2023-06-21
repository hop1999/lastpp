package webi.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Table(name = "users")
@Entity
@Data
public class User implements UserDetails {

    private static long serialVersionUid = 23212321831283L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 20, message = "User introducted incorrect value: field firstName")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @Size(min = 3, max = 20, message = "User introducted incorrect value: field lastName")
    private String lastname;

    @Column
    @Email
    private String email;

    @Column
    @Size(min = 8, max = 80, message = "User introdected incorrect value: field password")
    private String password;

    @Column
    private int age;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastname, int age, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String firstName, String lastname, int age, String email, String password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
