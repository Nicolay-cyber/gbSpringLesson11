package com.example.gbspringlesson11.repositories;

import com.example.gbspringlesson11.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Transactional
    @Modifying
    @Query("update Product p set p.cost = p.cost + ?1 where p.id = ?2")
    void changeCost(Integer delta, Long id);

}
