package com.fbitn.alexa.speechlet;

import java.time.LocalDate;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.fbitn.alexa.story.PolicyStory;

public class DueDateIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.intentName("DueDateIntent"));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		PolicyStory policyStory = new PolicyStory();
		String speechText;
		
		String policyNbr = (String)input.getAttributesManager().getSessionAttributes().get("policy_number");
		
		try {
			LocalDate dueDate = policyStory.retrieveDueDate(policyNbr);
			speechText = "Your next payment is due on " + dueDate;
		} catch(Exception e) {
			speechText = "The policy " + policyNbr + " was not found.";
		}			
		
		return input.getResponseBuilder()
				.withSpeech(speechText)				
				.withSimpleCard("BillingDateInfo", speechText)
				.withShouldEndSession(false)
				.build();
	}

}
