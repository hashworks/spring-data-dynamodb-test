package com.test.test.repo;

import com.test.test.model.Foo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

@EnableScan
@EnableScanCount
public interface FooRepository extends CrudRepository<Foo, String> {
  // List<Entrant> findByName(String name);
}
