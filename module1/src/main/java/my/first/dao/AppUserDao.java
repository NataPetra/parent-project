package my.first.dao;

import my.first.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserDao {

    List<AppUser> findByUsername(String username);

}
