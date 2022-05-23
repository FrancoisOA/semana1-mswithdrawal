package pe.com.bootcamp.microservice.withdraw.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bootcamp.microservice.withdraw.dto.WithdrawDTO;


@Repository
public interface IWithdrawRepository extends ReactiveMongoRepository<WithdrawDTO, String>{
}
