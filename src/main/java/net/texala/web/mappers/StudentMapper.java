package net.texala.web.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import net.texala.model.Student;
import net.texala.web.vo.StudentVo;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentVo toDto(Student student);

	Student toEntity(StudentVo studentVo);

	List<StudentVo> toDtos(List<Student> list);

	List<Student> toEntities(List<StudentVo> listvo);

}
