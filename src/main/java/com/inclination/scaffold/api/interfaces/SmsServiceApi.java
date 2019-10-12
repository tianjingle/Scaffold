package com.inclination.scaffold.api.interfaces;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//import com.inclination.scaffold.application.sms.SmsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.xml.ws.http.HTTPException;

@Api
@RestController
public class SmsServiceApi {
	
	//@Autowired
	//private SmsService smsService;
	
	@GetMapping(value="/sayHello.do")
	@ApiOperation(value="发送短信",notes="发送短信")
	public void sendSms() throws HTTPException, IOException{
	//	smsService.sendMsg();
	}
}
