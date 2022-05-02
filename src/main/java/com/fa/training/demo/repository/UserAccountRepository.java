package com.fa.training.demo.repository;

import com.fa.training.demo.entities.Role;
import com.fa.training.demo.entities.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    UserAccount findByUserName(String email);

    UserAccount findByPassword(String password);

    UserAccount findByUserNameIgnoreCase(String email);

    boolean existsByUserName(String email);

    @Query(value = "SELECT * FROM user_account u" +
            " INNER JOIN user_account_roles ur ON u.user_id = ur.user_account_list_user_id" +
            " INNER JOIN role r ON ur.roles_role_id = r.role_id" +
            " WHERE u.user_id IN (SELECT u2.user_id" +
                                " FROM user_account u2" +
                                " INNER JOIN user_account_roles ur2" +
                                " ON u2.user_id = ur2.user_account_list_user_id" +
                                " GROUP BY u2.user_id" +
                                " HAVING COUNT(*) = 1)" +
            " AND r.role_name LIKE 'ROLE_MEMBER'" +
            " ORDER BY u.is_activated DESC", nativeQuery = true)
    List<UserAccount> findEmployeeAccount(Pageable pageable);

    @Query(value = "SELECT * FROM user_account u" +
            " INNER JOIN user_account_roles ur ON u.user_id = ur.user_account_list_user_id" +
            " INNER JOIN role r ON ur.roles_role_id = r.role_id" +
            " WHERE u.user_id IN (SELECT u2.user_id" +
            " FROM user_account u2" +
            " INNER JOIN user_account_roles ur2" +
            " ON u2.user_id = ur2.user_account_list_user_id" +
            " GROUP BY u2.user_id" +
            " HAVING COUNT(*) = 1)" +
            " AND r.role_name LIKE 'ROLE_MEMBER'" +
            " ORDER BY u.is_activated DESC", nativeQuery = true)
    List<UserAccount> findEmployeeAccount();
}
