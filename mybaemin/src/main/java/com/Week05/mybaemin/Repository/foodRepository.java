package com.Week05.mybaemin.Repository;

import com.Week05.mybaemin.Entity.bregister;
import com.Week05.mybaemin.Entity.food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface foodRepository extends JpaRepository<food, Integer> {
    List<food> findAllByRid(int rid);

}
