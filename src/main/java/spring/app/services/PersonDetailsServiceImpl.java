package spring.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.app.models.Person;
import spring.app.repositories.PeopleRepository;
import spring.app.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsServiceImpl implements UserDetailsService {  // UserDetailsService for Security

    private final PeopleRepository peopleRepository;

    public PersonDetailsServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        if (person.isEmpty()) throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
