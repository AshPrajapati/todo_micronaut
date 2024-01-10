package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
  List<Todo> findAll();
}
