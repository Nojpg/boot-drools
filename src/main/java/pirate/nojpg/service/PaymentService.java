package pirate.nojpg.service;

import pirate.nojpg.model.Deposit;
import pirate.nojpg.model.Payment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

	private final KieContainer kieContainer;

	@Autowired
	public PaymentService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public Payment getProductDiscount(Payment payment, Deposit deposit) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(deposit);
		kieSession.setGlobal("payment", payment);
		kieSession.insert(payment);
		kieSession.fireAllRules();
		kieSession.dispose();
		return payment;
	}
}