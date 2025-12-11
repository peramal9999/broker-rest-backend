package com.radianbroker.enums;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Roles {

	MEMBER("Member"), 
	RADIANADMIN("RadianAdmin"), 
	GROUPADMIN("GroupAdmin"), 
	MRT("MRT"), 
	SMRT("SMRT"), 
	MIS("MIS"),
	TYP("TYP"), 
	REG("REG");

	private String role;

	private Roles(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public static Roles of(String role) {
		for (Roles e : values()) {
	        if (e.role.equals(role)) {
	            return e;
	        }
	    }
	    return null;
	}

	public static Set<String> getSessionRoles() {
		EnumSet<Roles> sessionRoles = EnumSet.noneOf(Roles.class);
		sessionRoles.add(MEMBER);
		sessionRoles.add(RADIANADMIN);
		sessionRoles.add(GROUPADMIN);
		sessionRoles.add(MIS);
		return sessionRoles.stream().map(sessionRole -> sessionRole.getRole()).collect(Collectors.toSet());
	}
}
