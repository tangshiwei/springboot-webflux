package com.webflux.dao;

import com.webflux.bean.PUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PUserDao extends JpaRepository<PUser, Long> {
}