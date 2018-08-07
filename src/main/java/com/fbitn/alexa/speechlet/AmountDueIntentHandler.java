package com.fbitn.alexa.speechlet;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.fbitn.alexa.story.PolicyStory;

public class AmountDueIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("AmountDueIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		PolicyStory policyStory = new PolicyStory();
		String speechText;
		
		String policyNbr = (String)input.getAttributesManager().getSessionAttributes().get("policy_number");
		
		try {
			String amtDue = policyStory.retrieveDueAmount(policyNbr);
			speechText = "Your next bill is for " + amtDue;
		} catch(Exception e) {
			speechText = "The policy " + policyNbr + " was not found.";
		}			
		
		return input.getResponseBuilder()
				.withSpeech(speechText)				
				.withSimpleCard("AmountDueInfo", speechText)
				.withShouldEndSession(false)
				.build();
	}

}
