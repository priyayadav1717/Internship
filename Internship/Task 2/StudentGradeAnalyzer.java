import java.util.*;

public class StudentGradeAnalyzer {

    static class Student {
        private String studentName;
        private List<Double> grades;

        public Student(String studentName) {
            this.studentName = studentName;
            this.grades = new ArrayList<>();
        }

        public String getStudentName() {
            return studentName;
        }

        public List<Double> getGrades() {
            return grades;
        }

        public void addGrade(double grade) {
            grades.add(grade);
        }

        public double calculateAverageGrade() {
            if (grades.isEmpty()) {
                return 0.0;
            }
            double sum = 0.0;
            for (Double grade : grades) {
                sum += grade;
            }
            return sum / grades.size();
        }

        @Override
        public String toString() {
            return "Student Name: " + studentName + "\nGrades: " + grades + "\n";
        }
    }

    public static class GradeAnalyzer {
        private List<Student> students;

        public GradeAnalyzer() {
            this.students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
            System.out.println("Student added successfully.");
        }

        public void analyzeGrades() {
            if (students.isEmpty()) {
                System.out.println("No students available for analysis.");
                return;
            }

            // Calculate average grades for each student
            System.out.println("\nAverage Grades for Each Student:");
            for (Student student : students) {
                double averageGrade = student.calculateAverageGrade();
                System.out.println(student.getStudentName() + ": " + averageGrade);
            }

            // Calculate overall class average
            double classAverage = calculateClassAverage();
            System.out.println("\nClass Average: " + classAverage);

            // Identify top-performing students
            List<Student> topPerformers = identifyTopPerformers(3);
            System.out.println("\nTop Performing Students:");
            for (Student student : topPerformers) {
                System.out.println(student);
            }

            // Identify areas for improvement
            List<Student> areasForImprovement = identifyAreasForImprovement(3);
            System.out.println("\nAreas for Improvement:");
            for (Student student : areasForImprovement) {
                System.out.println(student);
            }
        }

        private double calculateClassAverage() {
            double total = 0.0;
            for (Student student : students) {
                total += student.calculateAverageGrade();
            }
            return total / students.size();
        }

        private List<Student> identifyTopPerformers(int count) {
            List<Student> sortedStudents = new ArrayList<>(students);
            Collections.sort(sortedStudents, Comparator.comparingDouble(Student::calculateAverageGrade).reversed());
            return sortedStudents.subList(0, Math.min(count, sortedStudents.size()));
        }

        private List<Student> identifyAreasForImprovement(int count) {
            List<Student> sortedStudents = new ArrayList<>(students);
            Collections.sort(sortedStudents, Comparator.comparingDouble(Student::calculateAverageGrade));
            return sortedStudents.subList(0, Math.min(count, sortedStudents.size()));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeAnalyzer gradeAnalyzer = new GradeAnalyzer();

        while (true) {
            System.out.println("\nGrade Analyzer Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Analyze Grades");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    Student student = new Student(studentName);

                    System.out.print("Enter the number of grades for the student: ");
                    int numGrades = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    for (int i = 1; i <= numGrades; i++) {
                        System.out.print("Enter grade #" + i + ": ");
                        double grade = scanner.nextDouble();
                        student.addGrade(grade);
                    }

                    gradeAnalyzer.addStudent(student);
                    break;
                case 2:
                    gradeAnalyzer.analyzeGrades();
                    break;
                case 3:
                    System.out.println("Exiting Grade Analyzer.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
