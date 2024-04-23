package net.texala.enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

	Activated("Activated"), DEACTIVATED("DeActivated"), DELETED("Deleted");

	private String status;

	private Status(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}

	@JsonCreator
	public static Status decode(final String status) {
		return Stream.of(Status.values()).filter(x -> x.status.equalsIgnoreCase(status)).findFirst().orElse(null);
	}
}
