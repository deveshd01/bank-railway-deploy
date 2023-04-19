package com.pratiti.myBank.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pratiti.myBank.Entity.BankService;
import com.pratiti.myBank.Entity.Counter;
import com.pratiti.myBank.Entity.Token;
import com.pratiti.myBank.Model.TokenModel;
import com.pratiti.myBank.Repository.TokenRepo;
import com.pratiti.myBank.Repository.CounterRepo;
import com.pratiti.myBank.Repository.ServiceRepo;

@Service
public class TokenService {
	@Autowired
	private TokenRepo tokenRepo;

	@Autowired
	private CounterRepo counterRepo;

	@Autowired
	private ServiceRepo serviceRepo;

//	addToken**********************************************************************************
	public TokenModel newToken(Token token, int serviceId) {

		// Fetching Service By serviceName
		BankService service = serviceRepo.findById(serviceId).get();
		token.setService(service);

		Counter smallCounter = selectBestCounter(service); // counter with This Service & minimum Queue Length

		smallCounter.updateQueueTotalTime(token.getService());
		
		token.setCounter(smallCounter);		
		token.setCounterNumber(smallCounter.getCounterNo());
		
		tokenRepo.save(token);
		counterRepo.save(smallCounter);
		
		// TokenModel to Return
		TokenModel tm = new TokenModel();
		tm.setCounterNo(token.getCounterNumber());
		tm.setServiceName(token.getService().getServiceName());
		tm.setTokenNo(token.getTokenNo());
		tm.setStatus(TokenModel.Status.PENDING);
		// DATE Time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/YYYY");
		LocalDateTime now = LocalDateTime.now();
		int tSum = smallCounter.getQueueTotalTime();
		tm.setTimeGenerated(dtf.format(now));
		tm.setExpectedTime(dtf.format(now.plusMinutes(tSum)));

		return tm;
	}

	public void tokenProcessed(int tokenId) {
		Token token = tokenRepo.findById(tokenId).get();
		token.setStatus(Token.Status.SERVICED);
		token.setCounter(null);
		tokenRepo.save(token);
	}

	public Queue<Token> getFullQueue(int counterId) {
		Counter counter = counterRepo.findById(counterId).get();
		List<Token> tokenList = counter.getTokenList();
		Queue<Token> Q = new PriorityQueue<>();
		for (Token i : tokenList) {
			Q.add(i);
		}
		return Q;
	}

	public TokenModel nextToken(int counterId) {
		Counter counter = counterRepo.findById(counterId).get();
		List<Token> tokenList = counter.getTokenList();
		Token t = tokenList.get(0);
		for (Token i : tokenList) {
			if (t.compareTo(i) == 1)
				t = i;
		}
		
		TokenModel tm = new TokenModel();
		tm.setTokenNo(t.getTokenNo());
		tm.setTokenId(t.getId());
		tm.setServiceName(t.getService().getServiceName());

		// Remove token from counter Queue
		t.setCounter(null);
		tokenRepo.save(t);

		return tm;
	}
	
//	public int switchQueue(int tokenId) {
//		Token token = tokenRepo.findById(tokenId).get();
//		Counter bestCounter = selectBestCounter(token.getService());
//		
//		return 0;
//	}

//	--------------------------------------------------- private RAW Functions --------------------------------------------

	private Counter selectBestCounter(BankService service) {
		List<Counter> counters = tokenRepo.findCounterByServiceId(service.getS_id());
		Counter smallCounter = counters.get(0);
		for (Counter c : counters) {
			if (c.getQueueTotalTime() < smallCounter.getQueueTotalTime())
				smallCounter = c;
		}
		return smallCounter;
	}

//	public TokenModel findByTokenNo(int tokenId) {
//		Token token = tokenRepo.findById(tokenId).get();
//		
//		TokenModel tm = new TokenModel();
//		tm.setCounterNo(token.getCounterNumber());
//		tm.setServiceName(token.getService().getServiceName());
//		tm.setStatus(token.getStatus());
//		tm.
//		
//		return null;
//	}

	

}
