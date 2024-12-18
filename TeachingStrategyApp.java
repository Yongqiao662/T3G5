
package teaching.strategy.prediction.recommender.website;


import java.util.Scanner;

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Teacher {
    String name;
    String fieldOfStudy;
    int yearsOfExperience;
    String teachingStyle;
    boolean comfortWithTechnology;
    String[] coursesTaught; 

    public Teacher(String name, String fieldOfStudy, int yearsOfExperience, String teachingStyle, boolean comfortWithTechnology, String[] coursesTaught) {
        this.name = name;
        this.fieldOfStudy = fieldOfStudy;
        this.yearsOfExperience = yearsOfExperience;
        this.teachingStyle = teachingStyle;
        this.comfortWithTechnology = comfortWithTechnology;
        this.coursesTaught = coursesTaught;
    }
}

class Course {
    String name;
    String type; 
    int gradeLevel; 
    int classSize;

    public Course(String name, String type, int gradeLevel, int classSize) {
        this.name = name;
        this.type = type;
        this.gradeLevel = gradeLevel;
        this.classSize = classSize;
    }
}

class TeachingStrategyRecommender {

    public static String[] recommendStrategies(Course course, Teacher teacher) {
        String[] strategies = new String[10];  
        int index = 0;

        if (course.type.equalsIgnoreCase("Lecture")) {
            strategies[index++] = "Interactive Lectures";
            strategies[index++] = "Peer Instruction";
        } else if (course.type.equalsIgnoreCase("Lab")) {
            strategies[index++] = "Hands-On Activities";
            strategies[index++] = "Problem-Based Learning";
        } else if (course.type.equalsIgnoreCase("Seminar")) {
            strategies[index++] = "Debates";
            strategies[index++] = "Group Discussions";
        }

        if (teacher.comfortWithTechnology) {
            strategies[index++] = "Gamification";
        }

        if (teacher.yearsOfExperience > 10 && course.classSize > 30) {
            strategies[index++] = "Lecture-Based Teaching with Interactive Q&A";
        }

        // Return only the filled part of the array (ignoring null values)
        String[] result = new String[index];
        System.arraycopy(strategies, 0, result, 0, index);
        return result;
    }

    public static String getStrategyDescription(String strategy) {
        switch (strategy) {
            case "Interactive Lectures":
                return "Ask questions to the class regularly to keep students engaged.";
            case "Peer Instruction":
                return "Encourages learning through student discussions.";
            case "Hands-On Activities":
                return "Focus on active participation where students directly engage with the equipment, tools, or materials in the lab.";
            case "Problem-Based Learning":
                return "Present real-world problems in the lab that students must solve using the knowledge and skills they have gained.";
            case "Debates":
                return "Organize structured debates where students argue different perspectives on a given topic.";
            case "Group Discussions":
                return "Encourage students to break into group discussions around a central topic or question.";
            case "Gamification":
                return "Construct games like quizzes, challenges, and rewards into the learning process.";
            case "Lecture-Based Teaching with Interactive Q&A":
                return "Combines lectures with question-and-answer sessions to engage students.";
            default:
                return "Description not available.";
        }
    }

    public static void displayStrategiesWithDescriptions(String[] strategies) { 
        System.out.println("Recommended Teaching Strategies:");
        for (String strategy : strategies) {
            if (strategy != null) { 
                String description = getStrategyDescription(strategy);
                System.out.println("- " + strategy + ": " + description);
            }
        }
    }
}

public class TeachingStrategyApp {
    private static User[] userDatabase = new User[100]; 
    private static int userCount = 0; 
    
    public static void signUp(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        for (int i = 0; i < userCount; i++) { 
            if (userDatabase[i].username.equals(username)) {
                System.out.println("Username already exists. Please try logging in.");
                return;
            }
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        
        if (userCount < userDatabase.length) {
            userDatabase[userCount++] = new User(username, password);
            System.out.println("Sign Up successful! You can now log in.");
        } else {
            System.out.println("User database is full.");
        }
    }

    public static boolean logIn(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

       
        for (int i = 0; i < userCount; i++) {
            if (userDatabase[i].username.equals(username)) {
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (userDatabase[i].password.equals(password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    return true;
                } else {
                    System.out.println("Incorrect password. Please try again.");
                    return false;
                }
            }
        }

        
        System.out.println("Invalid username. Please sign up first.");
        return false;
    }

    public static void teachingStrategyApp(Scanner scanner) {
        System.out.println("\n--- Teaching Strategy Recommendation App ---");

       
        System.out.print("Enter teacher's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter field of study: ");
        String fieldOfStudy = scanner.nextLine();
        int experience = getValidIntInput(scanner, "Enter years of teaching experience: ");
        System.out.print("Enter teaching style: ");
        String teachingStyle = scanner.nextLine();
        boolean comfortWithTech = getValidBooleanInput(scanner, "Are you comfortable with technology? (true/false): ");

       
        int numberOfCourses = getValidIntInput(scanner, "Enter number of courses taught: ");
        String[] courses = new String[numberOfCourses]; 
        for (int i = 0; i < numberOfCourses; i++) {
            System.out.print("Enter course name " + (i + 1) + ": ");
            courses[i] = scanner.nextLine(); 
        }

        Teacher teacher = new Teacher(name, fieldOfStudy, experience, teachingStyle, comfortWithTech, courses);


        System.out.print("Enter course type (Lecture/Lab/Seminar): ");
        String courseType = scanner.nextLine();
        int gradeLevel = getValidIntInput(scanner, "Enter grade level (1 for Undergraduate, 2 for Postgraduate): ");
        int classSize = getValidIntInput(scanner, "Enter class size: ");
        
        Course course = new Course("Consolidated Course", courseType, gradeLevel, classSize);

        // Recommend and display strategies
        String[] strategies = TeachingStrategyRecommender.recommendStrategies(course, teacher);
        TeachingStrategyRecommender.displayStrategiesWithDescriptions(strategies);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            System.out.println("\n--- Welcome to the Teaching Strategy System ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    signUp(scanner); 
                    break;
                case "2":
                    isAuthenticated = logIn(scanner); 
                    if (isAuthenticated) {
                        teachingStrategyApp(scanner);
                    }
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public static int getValidIntInput(Scanner scanner, String str) {
        while (true) {
            try {
                System.out.print(str);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static boolean getValidBooleanInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
    }
}




