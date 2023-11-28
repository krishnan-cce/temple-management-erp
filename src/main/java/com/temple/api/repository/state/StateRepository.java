package com.temple.api.repository.state;

import com.temple.api.entity.state.StateMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.plaf.nimbus.State;

public interface StateRepository extends JpaRepository<StateMaster,Long> {
}
