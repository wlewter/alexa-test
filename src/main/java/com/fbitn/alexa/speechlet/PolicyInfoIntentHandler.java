package com.fbitn.alexa.speechlet;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.fbitn.alexa.story.PolicyStory;

public class PolicyInfoIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("PolicyInfoIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		PolicyStory policyStory = new PolicyStory();
		String speechText;
		
		Request request = input.getRequestEnvelope().getRequest();
		IntentRequest intentRequest = (IntentRequest) request;
		Intent intent = intentRequest.getIntent();		
		Map<String, Slot> slots = intent.getSlots();
		Slot policyNbrSlot = slots.get("policy_number");
		
		String policyNbr = policyNbrSlot.getValue();
		
		if( policyStory.validatePolicy(policyNbr) ) {
			input.getAttributesManager().getSessionAttributes().put("policy_number", policyNbr);
			speechText = "Your policy number was found. I can see when your next bill is due or tell you the next bill amount. What would you like to know?";
		} else {
			speechText = "Your policy number was not found: " + policyNbr;
		}
		
		return input.getResponseBuilder()
				.withSpeech(speechText)				
				.withSimpleCard("PolicyInfo", speechText)
				.withShouldEndSession(false)
				.build();
	}

}
