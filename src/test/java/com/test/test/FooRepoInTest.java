package com.test.test;

import static org.junit.jupiter.api.Assertions.*;

import com.test.test.model.*;
import com.test.test.repo.*;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FooRepoInTest implements WithAssertions {

  @Autowired FooRepository fooRepo;

  @BeforeEach
  void cleanDatabase() {
    fooRepo.deleteAll();
  }

  @Test
  void testRepo() {
    Foo foo = new Foo("bar");
    fooRepo.save(foo);
    assertEquals(1, fooRepo.count());
  }
}
