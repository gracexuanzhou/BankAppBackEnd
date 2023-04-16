/*import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("nl.rabobank")
public class AppConfig {


    @Bean(name = "userService")
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public UserService getUserService(){
        UserServiceImpl service = new UserServiceImpl(getUserRepository());
        //service.setRepository(getUserRepository());
        return service;
    }

    @Bean(name = "userRepository")
    public UserRepository getUserRepository(){
        return new HibernateUserRepositoryImpl();

    }
}*/
