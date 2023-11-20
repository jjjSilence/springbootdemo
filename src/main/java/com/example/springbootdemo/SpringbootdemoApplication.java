package com.example.springbootdemo;

import com.example.springbootdemo.student.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
    }

    @GetMapping
    public String getMessage() {
        return "hello world";
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        StudentIdCardRepository studentIdCardRepository) {
        return args -> {
//            Student maria = new Student(
//                    "maria",
//                    "jones",
//                    "maria.jones@wonders.com",
//                    5
//            );
//            Student maria2 = new Student(
//                    "maria",
//                    "jones",
//                    "maria2.jones@wonders.com",
//                    15
//            );
//            Student jojo = new Student(
//                    "jojo",
//                    "jones",
//                    "jojo.jones@wonders.com",
//                    6
//            );
//            System.out.println("Adding maria and jojo.");
//            studentRepository.saveAll(List.of(maria, maria2, jojo));
//
//            System.out.print("Number of students:");
//            System.out.println(studentRepository.count() + "");
//
//
//            studentRepository
//                    .findById(2L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.printf("Student with ID 2 not found")
//                    );
//            studentRepository
//                    .findById(3L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.printf("Student with ID 2 not found")
//                    );
//            System.out.println("Select all students");
//            List<Student> students = studentRepository.findAll();
//            students.forEach(System.out::println);
//
//            System.out.println("Delete maria");
//            studentRepository.deleteById(1L);
//
//            System.out.print("Number of students:");
//            System.out.println(studentRepository.count() + "");
//

//            studentRepository.findStudentByEmail("maria.jones@wonders.com")
//                    .ifPresentOrElse(System.out::println, ()->{
//                        System.out.println("Student with email maria.jones@wonders.com not found");
//            });
//            studentRepository.findStudentByFirstNameEqualsAndAge("maria", 5)
//                    .forEach(System.out::println);
//            studentRepository.findStudentByFirstNameEqualsAndAgeIsGreaterThan("maria", 5)
//                    .forEach(System.out::println);
//            studentRepository.findStudentByFirstNameEqualsAndAgeIsGreaterThanEqual("maria", 5)
//                    .forEach(System.out::println);
//            studentRepository.findStudentByFirstNameAndAgeIsGreaterOrEqual("maria", 5)
//                    .forEach(System.out::println);
//            studentRepository.findStudentByFirstNameAndAgeIsGreaterOrEqualNative2("maria", 5)
//                    .forEach(System.out::println);

//            System.out.println("Deleting Maria 2");
//            studentRepository.deleteStudentById(2L);
//
//            List<Student> students = studentRepository.findAll();
//            students.forEach(System.out::println);

//            generateRadomStudents(studentRepository);
//            sorting(studentRepository);
//            pageSorting(studentRepository);


            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@wonders.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));
            student.addBook(new Book("Clear Code", LocalDateTime.now().minusDays(4)));
            student.addBook(new Book("Think and Grow Rich", LocalDateTime.now()));
            student.addBook(new Book("Spring Data JPA", LocalDateTime.now().minusYears(4)));

            StudentIdCard studentIdCard = new StudentIdCard("1234567890", student);
            student.setStudentIdCard(studentIdCard);

//            student.enrolToCourse(new Course("Computer Science", "IT"));
//            student.enrolToCourse(new Course("Amigoscode Spring Data JPA", "IT"));

            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 1L),
                    student,
                    new Course("Computer Science", "IT"),
                    LocalDateTime.now().minusDays(4)
            ));
            student.addEnrolment(new Enrolment(
                    new EnrolmentId(1L, 2L),
                    student,
                    new Course("Amigoscode Spring Data JPA", "IT"),
                    LocalDateTime.now()
            ));

            studentRepository.save(student);

            studentRepository.findById(1L).ifPresent(
                    s -> {
                        System.out.println("fetch book lazy...");
                        List<Book> books = s.getBooks();
                        books.forEach(book -> {
                            System.out.println(s.getFirstName() + " borrowed " + book.getBookName());
                        });
                    });
//            studentIdCardRepository.findById(1L).ifPresent(System.out::println);
//            studentRepository.deleteById(1L);


        };
    }

    private static void pageSorting(StudentRepository studentRepository) {
        PageRequest pageRequest = PageRequest.of(
                0,
                5,
                Sort.by("firstName").ascending());
        Page<Student> page = studentRepository.findAll(pageRequest);
        System.out.println(page);
    }

    private static void sorting(StudentRepository studentRepository) {
//        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        Sort sort = Sort.by("firstName").ascending().and(Sort.by("age").descending());
        studentRepository.findAll(sort)
                .forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
    }

    private static void generateRadomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@wonders.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55)
            );
            studentRepository.save(student);
        }
    }
}
