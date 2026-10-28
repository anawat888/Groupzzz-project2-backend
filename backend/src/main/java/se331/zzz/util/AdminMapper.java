package se331.zzz.util;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.zzz.dto.*;
import se331.zzz.entity.*;
import java.util.List;
@Mapper 

public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    LevelSummaryDTO getLevelSummaryDto(Level level);
    LevelDTO getLevelDto(Level level);
    List<LevelDTO> getLevelDto(List<Level> levels);

    BenefitDTO getBenefitDto(Benefit benefit);
    ServiceItemDTO getServiceItemDto(ServiceItem serviceItem);

    QuestionDTO getQuestionDto(Question question);
    List<QuestionDTO> getQuestionDto(List<Question> questions);

    ExamQuestionDTO getExamQuestionDto(Question question);
    List<ExamQuestionDTO> getExamQuestionDto(List<Question> questions);

    ExamAttemptDTO getExamAttemptDto(ExamAttempt attempt);
    List<ExamAttemptDTO> getExamAttemptDto(List<ExamAttempt> attempts);
}
