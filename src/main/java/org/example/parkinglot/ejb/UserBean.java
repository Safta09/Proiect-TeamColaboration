package org.example.parkinglot.ejb;


import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.common.UserDto;
import org.example.parkinglot.entities.User;

@Stateless
public class UserBean {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;
    public List<UserDto> findAllUsers() {
        LOG.info("msg: \"findAllUsers\"");
        try {
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> userList) {
        List<UserDto> dtos = new ArrayList<UserDto>();
        for (User user : userList) {
            UserDto dto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()

            );
            dtos.add(dto);
        }
        return dtos;
}

}