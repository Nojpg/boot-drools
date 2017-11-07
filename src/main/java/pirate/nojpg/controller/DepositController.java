package pirate.nojpg.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pirate.nojpg.model.Deposit;
import pirate.nojpg.model.Payment;
import pirate.nojpg.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class DepositController {

	private final PaymentService paymentService;

	@Autowired
	public DepositController(PaymentService jewelleryShopService) {
		this.paymentService = jewelleryShopService;
	}

	@GetMapping(value = "/getPayment", produces = "application/json")
	public Payment getPayment(@RequestParam int sum, @RequestParam int term) {

		Deposit deposit = new Deposit();
		deposit.setSumOfDeposit(sum);
		deposit.setTermOfDeposit(term);
		Payment payment = new Payment();
		paymentService.getProductDiscount(payment, deposit);

		return payment;
	}

	@PostMapping(value = "/getPayment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Payment postPayment(@RequestBody Deposit deposit) {
		Payment payment = new Payment();
		paymentService.getProductDiscount(payment, deposit);
		return payment;
	}
}
