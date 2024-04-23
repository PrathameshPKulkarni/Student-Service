package net.texala.web.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.texala.enums.Status;

@Getter
@Setter
public class StudentVo {public StudentVo() {
	// TODO Auto-generated constructor stub
}
	@NotNull(message = "Student ID should not be Null.")
	private int id;

	@NotBlank(message = "Student Name should not be Null.")
	private String name;
	
	@NotBlank(message = "Student address should not be Null.")
	@Size(max =100000, min =0, message = "Salary should be between 0v to 100000")
	private String address;

	@NotNull(message = "Student Salary should not be Null.")
	@Min(value = 0, message = "minimum value is 0")
	@Max( value = 150000,message = "")
	private double salary;

	@NotNull(message = "Student Status should not be Null.")
	public Status status;

}
