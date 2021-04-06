package com.neliolucas.zeroip.repositories;

import com.neliolucas.zeroip.models.PublicIp;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Nelio
 * @date 24/03/2021
 */
public interface PublicIpRepository extends MongoRepository<PublicIp,String> {
}
