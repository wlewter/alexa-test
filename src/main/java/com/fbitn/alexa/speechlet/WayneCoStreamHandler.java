package com.fbitn.alexa.speechlet;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

public class WayneCoStreamHandler extends SkillStreamHandler {

	private static Skill getSkill() {
		return Skills.standard().addRequestHandlers(new LaunchRequestHandler(), new PolicyInfoIntentHandler(), new AmountDueIntentHandler(), new DueDateIntentHandler()).build();
	}
	
	public WayneCoStreamHandler() { 
		super(getSkill());
	}

}
