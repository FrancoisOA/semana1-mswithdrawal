package pe.com.bootcamp.microservice.withdraw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.withdraw.dto.WithdrawDTO;
import pe.com.bootcamp.microservice.withdraw.repository.IWithdrawRepository;
import pe.com.bootcamp.microservice.withdraw.service.WithdrawService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WithdrawServiceImpl implements WithdrawService {
	@Autowired
	private IWithdrawRepository iWithdrawRepo;

	@Override
    public void createWit(WithdrawDTO withdraw) {
    	iWithdrawRepo.save(withdraw).subscribe();
    }

	@Override
    public Mono<WithdrawDTO> findByWitId(String id) {
        return iWithdrawRepo.findById(id);
    }
	
	@Override
    public Flux<WithdrawDTO> findAllWit() {
        return iWithdrawRepo.findAll();
    }

	@Override
    public Mono<WithdrawDTO> updateWit(String id, WithdrawDTO withdraw) {
        return iWithdrawRepo.findById(id)
                .switchIfEmpty(Mono.error(new Exception("TASK_NOT_FOUND")))
                .map(fetchedWithdraw  -> {
                	withdraw.setId(id);
		                if (withdraw.getIdCustomer() != null) {fetchedWithdraw.setIdCustomer(withdraw.getIdCustomer());}
		                if (withdraw.getIdAccount() != null) {fetchedWithdraw.setIdAccount(withdraw.getIdAccount());}
		                if (withdraw.getNumRetirementAccount() != null) {fetchedWithdraw.setNumRetirementAccount(withdraw.getNumRetirementAccount());}
		                if (withdraw.getNameRetirementBank() != null) {fetchedWithdraw.setNameRetirementBank(withdraw.getNameRetirementBank());}
		                if (withdraw.getAmountRetirement() != null) {fetchedWithdraw.setAmountRetirement(withdraw.getAmountRetirement());}
		                if (withdraw.getDateRetirement() != null) {fetchedWithdraw.setDateRetirement(withdraw.getDateRetirement());}
		                if (withdraw.getTimeRetirement() != null) {fetchedWithdraw.setTimeRetirement(withdraw.getTimeRetirement());}
		                if (withdraw.getBankingChannel() != null) {fetchedWithdraw.setBankingChannel(withdraw.getBankingChannel());}
                    if (withdraw.getRetirementConcept() != null) {fetchedWithdraw.setRetirementConcept(withdraw.getRetirementConcept());}
                    if (withdraw.getRetirementStatus() != null) {fetchedWithdraw.setRetirementStatus(withdraw.getRetirementStatus());}
                    if (withdraw.getComission() != 0.0) {fetchedWithdraw.setComission(withdraw.getComission());}
                    return fetchedWithdraw;
                })
                .flatMap(iWithdrawRepo::save);
    }

	@Override
    public Mono<Void> deleteWit(String id) {
        return iWithdrawRepo.deleteById(id);
    }
}
