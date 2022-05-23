package pe.com.bootcamp.microservice.withdraw.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 
@Document(collection = "tb_withdraw")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WithdrawDTO {
	@Id
	private String id;
	private String idCustomer;
	private String idAccount;
	private String numRetirementAccount;
	private String nameRetirementBank;
	private String amountRetirement;
	private String dateRetirement;
	private String timeRetirement;
	private String bankingChannel;
	private String retirementConcept;
	private String retirementStatus;
	private double comission;
}