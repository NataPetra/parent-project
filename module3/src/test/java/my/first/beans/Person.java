package my.first.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

@Getter
@Setter
public class Person implements InitializingBean, DisposableBean, ApplicationContextAware {

    private String secondName;
    private String name;
    private AbstractAddress homeAddress;
    private Map<Integer, Person> children;

    public Person() {
        System.out.println("Calling Person()" + name + " " + secondName);
    }

    public void init(){
        System.out.println("Calling init()");
    }

    public void destroy(){
        System.out.println("Calling destroy()");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Calling afterPropertiesSet()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Calling setApplicationContext()");
    }
}
