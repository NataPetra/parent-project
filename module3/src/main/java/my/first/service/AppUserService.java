package my.first.service;

import my.first.dao.AppUserDao;
import my.first.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    public List<AppUser> findUserByUsername(String username) {
        return appUserDao.findByUsername(username);
    }

}
