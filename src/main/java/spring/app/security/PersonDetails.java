package spring.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import spring.app.models.Person;

import java.util.Collection;


/**
 * A wrapper class over a model that provides information about it.
 * We can say that this class sets the standard by which you can use the model.
 * For example, thanks to it, the method of getting a password will always have one name.
 */

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Method for getting user roles
    }

    @Override
    public String getPassword() {
        return this.person.getPassword();
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
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

    // to get authenticated person data
    public Person getPerson() {
        return this.person;
    }
}
