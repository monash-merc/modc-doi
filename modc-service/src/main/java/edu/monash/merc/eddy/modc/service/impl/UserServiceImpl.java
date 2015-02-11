package edu.monash.merc.eddy.modc.service.impl;

import edu.monash.merc.eddy.modc.common.ldap.LdapUser;
import edu.monash.merc.eddy.modc.common.util.MD5;
import edu.monash.merc.eddy.modc.dao.UserDAO;
import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.service.UserService;
import edu.monash.merc.eddy.modc.service.ldap.LdapService;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by simonyu on 8/08/2014.
 */
@Service
@Qualifier("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private LdapService ldapService;

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void setLdapService(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @Override
    public void saveUser(User user) {
        this.userDao.add(user);
    }

    @Override
    public User getUserById(long id) {
        return this.userDao.get(id);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userDao.remove(user);
    }

    @Override
    public void deleteUserById(long userId) {
        this.userDao.delete(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userDao.getUserByEmail(email);
    }

    @Override
    public User getUserByUniqueId(String uniqueId) {
        return this.userDao.getUserByUniqueId(uniqueId);
    }

    @Override
    public boolean checkExistedName(String displayName) {
        return this.userDao.checkExistedName(displayName);
    }

    @Override
    public boolean checkExistedUniqueId(String uniqueId) {
        return this.userDao.checkExistedUniqueId(uniqueId);
    }

    @Override
    public boolean checkExistedEmail(String email) {
        return this.userDao.checkExistedEmail(email);
    }

    @Override
    public User login(String uniqueId, String password, boolean ldapSupported) {
        String md5pwd = MD5.hash(password);
        User user = this.userDao.checkUserLogin(uniqueId, md5pwd);
        if (user != null) {
            return user;
        }

        if (ldapSupported) {
            user = this.userDao.getUserByUniqueId(uniqueId);
            if (user != null) {
                boolean logined = this.ldapService.login(uniqueId, password);
                if (logined) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public Pager<User> getUsers(int startPageNo, int sizePerPage, Order[] orderParams) {
        return this.userDao.getUsers(startPageNo, sizePerPage, orderParams);
    }
}
