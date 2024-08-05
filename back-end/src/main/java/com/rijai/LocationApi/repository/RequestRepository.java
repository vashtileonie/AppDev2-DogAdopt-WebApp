package com.rijai.LocationApi.repository;

import com.rijai.LocationApi.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository <Request, Long> {
}
