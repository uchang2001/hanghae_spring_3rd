package com.Week05.mybaemin.Repository;

import com.Week05.mybaemin.Entity.bregister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface bregisterRepository extends JpaRepository<bregister, Integer> {

}
