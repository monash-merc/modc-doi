package edu.monash.merc.eddy.modc.service;

import edu.monash.merc.eddy.modc.common.ldap.LdapUser;
import edu.monash.merc.eddy.modc.domain.User;
import edu.monash.merc.eddy.modc.sql.page.Pager;
import org.hibernate.criterion.Order;

/**
 * Created by simonyu on 8/08/2014.
 */
public interface UserService {

    void saveUser(User user);

    User getUserById(long id);

    void updateUser(User user);

    void deleteUser(User user);

    void deleteUserById(long userId);

    User getUserByEmail(String email);

    User getUserByUniqueId(String uniqueId);

    boolean checkExistedName(String displayName);

    boolean checkExistedUniqueId(String uniqueId);

    boolean checkExistedEmail(String email);

    User login(String uniqueId, String password, boolean ldapSupported);

    Pager<User> getUsers(int startPageNo, int sizePerPage, Order[] orderParams);
}
