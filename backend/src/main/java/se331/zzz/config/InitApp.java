package se331.zzz.config;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.zzz.entity.*;
import se331.zzz.repository.*;
import java.util.List;

@Component 
@RequiredArgsConstructor

public class InitApp implements ApplicationListener<ApplicationReadyEvent>{
    final LevelRepository levelRepository;
    final BenefitRepository benefitRepository;
    final ServiceItemRepository serviceItemRepository;
    final QuestionRepository questionRepository;
    final ElearningContentRepository elearningContentRepository;

    @Override 
    @Transactional 
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent){
        Level level1 = levelRepository.save(Level.builder().name("Level 1 Addition & Subtraction").description("Adding and subtracting integers is the starting level for everyone").passScore(60).build());
        Level level2 = levelRepository.save(Level.builder().name("Level 2 Multiplication & Division").description("Multiply and divide integers, including multiplication tables").passScore(70).build());
        Level level3 = levelRepository.save(Level.builder().name("Level 3 Fractions & Decimals").description("Fractions and Decimals: Addition, Subtraction, Multiplication, and Division of Fractions").passScore(80).build());
        Level level4 = levelRepository.save(Level.builder().name("Level 4 Basic Algebra").description("Introduction to Algebra: Solving Equations for x").passScore(90).build());

        addBenefit(level1, "Do basic exercises", "Practice addition and subtraction problems an unlimited number of times.");
        addBenefit(level1, "Watch the Level 1 lesson video", "Access addition and subtraction tutorial video for Level 1");
        addBenefit(level1, "Passed the level advancement exam", "You can take the exam to advance to Level 2 at any time");
        addBenefit(level2, "All services and benefits of Level 1", "You get everything available at Level 1");
        addBenefit(level2, "View the detailed solution", "View the step-by-step solution after submitting your answer");
        addBenefit(level2, "Multiplication and division problem bank", "Multiplication and division problems with selectable difficulty levels");
        addBenefit(level3, "All services and benefits of Level 2", "You get everything available at Level 2");
        addBenefit(level3, "Summary of Fraction Formulas", "Download the summary sheet for fractions and decimals formulas");
        addBenefit(level3, "Historical exam results statistics", "View the graph of your score progression");
        addBenefit(level4, "All services and benefits of Level 3", "You get everything available at Level 3");
        addBenefit(level4, "Advanced algebra problems", "Access the most difficult set of equation problems in the system");
        addBenefit(level4, "Expert badge", "The highest-level badge is displayed on the profile page");

        addService(level1, "Basic calculator", "Addition, subtraction, multiplication, and division of integers");
        addService(level1, "Addition and subtraction problem bank", "Addition and subtraction practice problems with answers");
        addService(level1, "Multiplication table", "View the multiplication tables for 2 through 12");
        addService(level2, "Factorization tool", "Automatically factorize integers");
        addService(level2, "Daily Multiplication and Division Problems", "Random multiplication and division problems generated daily");
        addService(level2, "Time yourself while solving the problems", "Practice mental math speed");
        addService(level3, "Fraction calculator", "Calculate fractions and show the steps");
        addService(level3, "Convert fractions to decimals", "Convertible back and forth in both directions");
        addService(level3, "Fraction Problem Bank", "Multi-level fraction and decimal problems");
        addService(level4, "Equation solver", "Solve the equation for x and explain step-by-step");
        addService(level4, "Plot the graph of the equation", "Plot a graph from the entered equation");
        addService(level4, "Algebra Tutor", "A system that recommends problems based on the learner's weaknesses");

        addQuestion(level1, "What is 5 + 7?", List.of("10", "11", "12", "13"), "12");
        addQuestion(level1, "What is 9 - 4?", List.of("4", "5", "6", "3"), "5");
        addQuestion(level1, "What is 12 + 8?", List.of("18", "19", "20", "21"), "20");
        addQuestion(level1, "What is 15 - 6?", List.of("9", "8", "10", "7"), "9");
        addQuestion(level1, "What is 23 + 17?", List.of("38", "39", "40", "41"), "40");
        addQuestion(level2, "What is 8 × 9?", List.of("64", "70", "72", "81"), "72");
        addQuestion(level2, "What is 56 ÷ 8?", List.of("6", "7", "8", "9"), "7");
        addQuestion(level2, "What is 12 × 5?", List.of("50", "55", "60", "65"), "60");
        addQuestion(level2, "What is 144 ÷ 12?", List.of("10", "11", "12", "13"), "12");
        addQuestion(level2, "What is 100 ÷ 4?", List.of("20", "25", "30", "35"), "25");
        addQuestion(level3, "What is 1/2 + 1/4?", List.of("1/4", "2/4", "3/4", "1"), "3/4");
        addQuestion(level3, "What is 0.5 + 0.25?", List.of("0.65", "0.7", "0.75", "0.8"), "0.75");
        addQuestion(level3, "What is 1.5 + 2.25?", List.of("3.5", "3.75", "4.0", "4.25"), "3.75");
        addQuestion(level3, "What is 5.5 - 2.25?", List.of("3.0", "3.25", "3.5", "3.75"), "3.25");
        addQuestion(level3, "What is 1/2 × 1/2?", List.of("1/8", "1/4", "1/2", "1"), "1/4");
        addQuestion(level4, "Solve for x: x + 5 = 12", List.of("5", "6", "7", "8"), "7");
        addQuestion(level4, "Solve for x: 3x = 15", List.of("3", "4", "5", "6"), "5");
        addQuestion(level4, "What is x if x/2 = 9?", List.of("16", "17", "18", "19"), "18");
        addQuestion(level4, "Simplify: 3x + 2x", List.of("4x", "5x", "6x", "5x^2"), "5x");
        addQuestion(level4, "Solve for x: 5x = 2x + 9", List.of("2", "3", "4", "5"), "3");

        addElearning(level1, "Addition & Subtraction", "Learn to add and subtract integers", "https://youtube.com/embed/wNhbz1EUPrc");
        addElearning(level2, "Multiplication & Division", "Learn to multiply and divide integers", "https://youtube.com/embed/e7WGZHTZm0I");
        addElearning(level3, "Fractions & Decimals", "Learn fractions and decimals", "https://youtube.com/embed/Vb5c7ay0ziU");
        addElearning(level4, "Basic Algebra", "Introduction to solving for x", "https://youtube.com/embed/kWOTmyoaWJg");
    }

    private void addBenefit(Level level,String title,String description){
        Benefit benefit = benefitRepository.save(Benefit.builder().title(title).description(description).build());
        benefit.setLevel(level);
        level.getBenefits().add(benefit);
    }
    private void addQuestion(Level level, String text,List<String> choices,String correctAnswer){
        Question question = questionRepository.save(Question.builder().question(text).choices(choices).correctAnswer(correctAnswer).build());
        question.setLevel(level);
        level.getQuestions().add(question);
    }
    private void addService(Level level,String name,String description){
        ServiceItem service = serviceItemRepository.save(ServiceItem.builder().name(name).description(description).build());
        service.setLevel(level);
        level.getServices().add(service);
    }

    private void addElearning(Level level, String title, String description, String videoUrl) {
        ElearningContent content = elearningContentRepository.save(
                ElearningContent.builder().title(title).description(description).videoUrl(videoUrl).build());
        content.setLevel(level);
    }
    
}
