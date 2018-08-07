package com.fbitn.alexa.story;

import java.time.LocalDate;

public class PolicyStory {

	public LocalDate retrieveDueDate(String policyNbr) {
		
		if( "12345".matches(policyNbr) ) {
			return LocalDate.now().plusDays(15);
		} else {
			throw new RuntimeException("Policy not found");
		}

	}
	
	public String retrieveDueAmount(String policyNbr) {
		
		if( "12345".matches(policyNbr) ) {
			return "$120.00";
		} else {
			throw new RuntimeException("Policy not found");
		}

	}
	
	public boolean validatePolicy(String policyNbr) {
		if( "12345".matches(policyNbr)) {
			return true;
		} else {
			return false;
		}
	}
}
