package com.olusola.videorental.model.user_db;

import com.olusola.videorental.security.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Setter
@Getter
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 64)
    private String firstname;

    @NotBlank
    @Size(max = 64)
    private String lastname;

    @NotBlank
    @NaturalId
    @Size(max = 64)
    private String email;

    private String password;
    private String username;


    @NotBlank
    @Size(max = 64)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CreditCard> creditCards = new ArrayList<>();

    public User(@NotBlank @Size(max = 64) String firstname, @NotBlank @Size(max = 64) String lastname,
                @NotBlank @Size(max = 64) String email, @NotBlank @Size(max = 64) String phone, String password, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

    public User() {

    }
}
