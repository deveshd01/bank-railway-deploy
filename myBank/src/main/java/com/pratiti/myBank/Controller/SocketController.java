package com.pratiti.myBank.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.pratiti.myBank.Model.SocketData;

@Controller
public class SocketController {
	
//	@Autowired
//	private SocketController socketController;
	
	@MessageMapping("/socketMessage") 			// /app/message
	@SendTo("/detailsScreen/public")						// send received msg to all those who subscribed this URL
	private SocketData receivePublicMessage(@Payload SocketData data) {
		System.out.println("---------------------->message 1 = " + data);
		return data;
	}

}
