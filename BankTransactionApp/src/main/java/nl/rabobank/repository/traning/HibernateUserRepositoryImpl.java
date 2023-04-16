/*package nl.rabobank.repository;

import nl.rabobank.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userRepository")
public class HibernateUserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findAll(){
        List<User> users = new ArrayList<User>();

        User user = new User();

        user.setUsername("xuan");
        user.setPassword("xuan");
        user.getBankAccount().setBankAccountID(118L);
        user.setId(1L);
        users.add(user);

        return users;
    }

}*/
