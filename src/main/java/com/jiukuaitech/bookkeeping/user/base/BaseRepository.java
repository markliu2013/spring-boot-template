package com.jiukuaitech.bookkeeping.user.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/*
https://www.baeldung.com/spring-data-jpa-method-in-all-repositories
https://github.com/pkainulainen/spring-data-jpa-examples/blob/master/custom-method-all-repos/src/main/java/net/petrikainulainen/springdata/jpa/common/BaseRepository.java
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {


}
