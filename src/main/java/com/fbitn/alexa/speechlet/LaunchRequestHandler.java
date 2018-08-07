package com.fbitn.alexa.speechlet;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class LaunchRequestHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.requestType(LaunchRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "Welcome to the WayneCo Skill. Tell me your policy number to continue";		
		return input.getResponseBuilder()
				.withSpeech(speechText)
				.withSimpleCard("PolicyInfo", speechText)
				.withReprompt(speechText)
				.build();
	}



}
