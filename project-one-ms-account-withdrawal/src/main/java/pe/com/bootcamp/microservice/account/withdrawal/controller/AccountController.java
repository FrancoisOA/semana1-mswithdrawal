package pe.com.bootcamp.microservice.account.withdrawal.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.account.withdrawal.dto.AccountDTO;
import pe.com.bootcamp.microservice.account.withdrawal.entity.Account;
import pe.com.bootcamp.microservice.account.withdrawal.service.AccountService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/account")
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private final AccountService accountService;
 

	@GetMapping(path = "/list")
	public Mono<ResponseEntity<Flux<Account>>> getAllAccount() {
		Flux<Account> list=this.accountService.getAllAccount();
		return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}	   
 
	   

	@PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AccountDTO> create(@RequestBody Mono<AccountDTO> accountDTO) {
        return this.accountService.createAccount(accountDTO);
    }
    

		 

	@PutMapping("update/{id}")
	private ResponseEntity<Mono<AccountDTO>> updateAccountById(@PathVariable String id, @RequestBody Mono<AccountDTO> accountdto){
 
		Account accountRequest = modelMapper.map(accountdto, Account.class);
		Mono<Account> account =  accountService.updateAccount(id, accountRequest);
		
		Mono<AccountDTO> accountResponse = Mono.just(modelMapper.map(account, AccountDTO.class));
		return ResponseEntity.ok().body(accountResponse); 
	}

	@DeleteMapping("delete/{id}")
	private Mono<ResponseEntity<Void>> deleteAccountById(@PathVariable String id){
		return this.accountService.deleteAccount(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/details/{id}")
	private ResponseEntity<Mono<AccountDTO>> getAccountById(@PathVariable String id){
 
		Mono<Account> account= accountService.getAccountById(id);

		Mono<AccountDTO> accountResponse = Mono.just(modelMapper.map(account, AccountDTO.class));
		return ResponseEntity.ok().body(accountResponse);
		 


	}
}