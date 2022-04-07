package com.Week05.mybaemin.Repository;

import com.Week05.mybaemin.Entity.myorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderRepository extends JpaRepository<myorder,Integer> {
}
