package com.temple.api.repository.temple;

import com.temple.api.entity.temple.Temple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempleRepository extends JpaRepository<Temple, Long> {
}
