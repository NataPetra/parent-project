package my.first.beans;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class PersonServiceTest {

    @Test
    public void testStringAnnotation(){
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(PersonService.class);

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        PersonService personService = context.getBean("personService", PersonService.class);
        personService.print(new Person());

        PersonService personService2 = context.getBean("personService", PersonService.class);
        personService2.print(new Person());

    }
}
