package my.first.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//аннотация класса для спринга
@Service

//для того, чтоб бин не был синглтоном - ("prototype")
@Scope

public class PersonService {

    public void print(Person person) {
        System.out.println(person);
    }
}
