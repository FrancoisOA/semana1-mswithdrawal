package pe.com.bootcamp.microservice.withdraw.service;

import pe.com.bootcamp.microservice.withdraw.dto.WithdrawDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WithdrawService {
    public void createWit(WithdrawDTO w);    
    public Mono<WithdrawDTO> findByWitId(String w); 
    public Flux<WithdrawDTO> findAllWit(); 
    public Mono<WithdrawDTO> updateWit(String id, WithdrawDTO d); 
    public Mono<Void> deleteWit(String id);   
}
