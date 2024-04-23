package net.texala.response.entity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestCustom {

	private String cause;

	public void setMessage(Exception e) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(baos);

		if (e != null) {
			e.printStackTrace(ps);
		}
		ps.close();
		cause = baos.toString();
	}

}
