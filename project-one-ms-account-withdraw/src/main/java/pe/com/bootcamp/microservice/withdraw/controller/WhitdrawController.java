package pe.com.bootcamp.microservice.withdraw.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.withdraw.dto.WithdrawDTO;
import pe.com.bootcamp.microservice.withdraw.service.impl.WithdrawServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/withdraw")
public class WhitdrawController {

	@Autowired
	private final WithdrawServiceImpl withdrawServiceImpl;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createWithdrawDTO(@RequestBody WithdrawDTO withdrawDTO) {
		withdrawServiceImpl.createWit(withdrawDTO);
	}

	@GetMapping(value = "/get/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseBody
	public Flux<WithdrawDTO> findAll() {
		return withdrawServiceImpl.findAllWit();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Mono<WithdrawDTO>> findWithdrawDTOById(@PathVariable("id") String id) {
		Mono<WithdrawDTO> withdraw = withdrawServiceImpl.findByWitId(id);
		return new ResponseEntity<Mono<WithdrawDTO>>(withdraw, withdraw != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<WithdrawDTO> update(@PathVariable("id") String id, @RequestBody WithdrawDTO withdrawDTO) {
		return withdrawServiceImpl.updateWit(id, withdrawDTO);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") String id) {
		withdrawServiceImpl.deleteWit(id).subscribe();
	}
}