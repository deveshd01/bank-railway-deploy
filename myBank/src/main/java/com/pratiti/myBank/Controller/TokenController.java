package com.pratiti.myBank.Controller;


import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.myBank.Entity.Token;
import com.pratiti.myBank.Entity.Token.Status;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.RequestStatus;
import com.pratiti.myBank.Model.TokenModel;
import com.pratiti.myBank.Service.TokenService;
import com.pratiti.myBank.MyBankApplication;

@RestController
@CrossOrigin
public class TokenController {

	@Autowired
	private TokenService tokenService;

	@GetMapping("/generateToken")
	public TokenModel generateToken(@RequestParam int serviceId) {
		Token token = new Token();
		token.setTokenNo(++MyBankApplication.TokenNumber);
		token.setStatus(Status.PENDING);
		
		try {
			return tokenService.newToken(token, serviceId );
		}
		catch(MyException e) {
			MyBankApplication.TokenNumber--;
			return null;
		}
	}
	
	@GetMapping("/tokenProcessed")
	public RequestStatus tokenProcessed(@RequestParam int tokenId) {
		RequestStatus status = new RequestStatus();
		try {
			tokenService.tokenProcessed(tokenId);
			status.setStatus(true);
			status.setMessage("Token Processed Complete...!!!");
			status.setId(tokenId);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}
	
//	@GetMapping("/getFullQueue")
//	public Queue<Token> getFullQueue(@RequestParam int counterId) {
//			return tokenService.getFullQueue(counterId );
//	}
//	
//	@GetMapping("/nextToken")
//	public TokenModel nextToken(@RequestParam int counterId) {
//			return tokenService.nextToken(counterId );
//	}
	
	
	
	
	
	
	
	
	
	@GetMapping("/getFullQueue")
	public Queue<Token> getFullQueue(@RequestParam int counterId) {
			return tokenService.findAllActiveTokens();
	}
	
	
	@GetMapping("/nextToken")
	public TokenModel nextToken(@RequestParam int counterId) {
			return tokenService.nextActiveToken();
	}
	
//	@GetMapping("/findByTokenNo")
//	public TokenModel findByTokenNo(@RequestParam int tokenId) {
//			return tokenService.findByTokenNo(tokenId );
//	}

	
//	@GetMapping("/switchQueue")
//	public RequestStatus switchQueue(@RequestParam int tokenId) {
//		
//		RequestStatus status = new RequestStatus();
//		try {
//			int newCounterNumber = TokenService.switchQueue(tokenId);
//			status.setStatus(true);
//			status.setMessage("You can switch to counter --> "+newCounterNumber);
//			status.setId(newCounterNumber);
//		}
//		catch(MyException e) {
//			status.setStatus(false);
//			status.setMessage(e.getMessage());
//		}
//		return status;
//	}

}
