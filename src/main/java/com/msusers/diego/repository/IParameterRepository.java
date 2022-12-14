package com.msusers.diego.repository;

import com.msusers.diego.entities.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParameterRepository extends JpaRepository<ParameterEntity,String> {

    ParameterEntity findByName(String name);
}
