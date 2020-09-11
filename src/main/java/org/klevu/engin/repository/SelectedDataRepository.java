package org.klevu.engin.repository;

import java.util.List;

import org.klevu.engin.entity.SelectedData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SelectedDataRepository extends MongoRepository<SelectedData, String> {

	@Query(value = "{'productId' : ?0 }")
	public List<SelectedData> findByProductId(String productId);
}
