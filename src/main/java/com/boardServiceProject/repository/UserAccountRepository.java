package com.boardServiceProject.repository;

import com.boardServiceProject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository  extends JpaRepository<UserAccount, String> {
}
