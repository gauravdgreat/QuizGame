package com.mycompany.quizgame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class QuizGame {
    private static int highestScore = 0;
    private static boolean quitGame = false;
    private static int currentLevel = 1;

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("\t\t\t\tWELCOME");
        System.out.println("\t\t\t\t   TO");
        System.out.println("\t\t\t\t GAME");
        System.out.println("----------------------------CREATED BY GAURAV--------------------------------------------------------------");

        try (Scanner scanner = new Scanner(System.in)) {
            while (!quitGame) {
                showMenu();
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "s" -> startGame();
                    case "v" -> viewHighestScore();
                    case "r" -> resetScore();
                    case "h" -> showHelp();
                    case "q" -> quitGame = true;
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
            
            System.out.println("Thanks for playing the Quiz Game!");
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("s - Start the game");
        System.out.println("v - View the highest score");
        System.out.println("r - Reset the score");
        System.out.println("h - Help");
        System.out.println("q - Quit");
        System.out.print("Enter your choice: ");
    }

    private static void startGame() {
        System.out.println("Starting the game...");
        int score = 0;
        int durationInSeconds;

        if (currentLevel == 1) {
            score = askQuestionsWithTimer(10, 60, currentLevel);
            if (score == 10) {
                System.out.println("Congratulations! You passed Level 1.");
                currentLevel++;
            } else {
                System.out.println("Sorry, you failed Level 1. Returning to the main screen.");
                return;
            }
        }

        if (currentLevel == 2) {
            durationInSeconds = 30;
            score += askQuestionsWithTimer(10, durationInSeconds, currentLevel);
            if (score == 20) {
                System.out.println("Congratulations! You passed Level 2.");
                currentLevel++;
            } else {
                System.out.println("Sorry, you failed Level 2. Returning to the main screen.");
                return;
            }
        }

        if (currentLevel == 3) {
            durationInSeconds = 15;
            score += askQuestionsWithTimer(10, durationInSeconds, currentLevel);
            if (score == 30) {
                System.out.println("Congratulations! You are a Quiz Master!");
            } else {
                System.out.println("Sorry, you failed the final level. Returning to the main screen.");
            }
        }

        if (score > highestScore) {
            highestScore = score;
            System.out.println("New highest score: " + highestScore);
        }
    }

    private static int askQuestionsWithTimer(int numQuestions, int durationInSeconds, int level) {
        int score = 0;
        Timer timer = new Timer();
        try (Scanner scanner = new Scanner(System.in)) {
            List<Integer> usedQuestionIndices = new ArrayList<>();
            for (int i = 1; i <= numQuestions; i++) {
                int questionIndex = getRandomQuestionIndex(level, usedQuestionIndices);
                if (questionIndex == -1) {
                    break;
                }
                usedQuestionIndices.add(questionIndex);
                
                System.out.println("Question " + i + ":");
                String question = getQuestion(level, questionIndex);
                System.out.println(question);
                String answer = getAnswer(level, questionIndex);
                
                System.out.print("Enter your answer (A/B/C/D): ");
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase(answer)) {
                    score++;
                }
                
                if (i != numQuestions) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("\nTime's up! Moving to the next question.\n");
                        }
                    }, TimeUnit.SECONDS.toMillis(durationInSeconds));
                }
            }
            
            timer.cancel();
        }
        return score;
    }

    private static int getRandomQuestionIndex(int level, List<Integer> usedQuestionIndices) {
        int maxQuestions = 10;
        List<Integer> allQuestionIndices = new ArrayList<>();
        for (int i = 1; i <= maxQuestions; i++) {
            allQuestionIndices.add(i);
        }
        allQuestionIndices.removeAll(usedQuestionIndices);

        if (allQuestionIndices.isEmpty()) {
            return -1; // No more available questions
        }

        Random random = new Random();
        int randomIndex = random.nextInt(allQuestionIndices.size());
        return allQuestionIndices.get(randomIndex);
    }

    private static String getQuestion(int level, int questionIndex) {
        switch (level) {
            case 1 -> {
            return switch (questionIndex) {
                case 1 -> "Which planet is known as the Red Planet?\nA. Mars\nB. Jupiter\nC. Saturn\nD. Venus";
                case 2 -> "Who is the current Prime Minister of India?\nA. Narendra Modi\nB. Rahul Gandhi\nC. Arvind Kejriwal\nD. Manmohan Singh";
                case 3 -> "What is the capital of Australia?\nA. Sydney\nB. Melbourne\nC. Canberra\nD. Brisbane";
                case 4 -> "Who wrote the famous novel 'To Kill a Mockingbird'?\nA. Mark Twain\nB. Harper Lee\nC. J.D. Salinger\nD. George Orwell";
                case 5 -> "Which country is known as the 'Land of the Rising Sun'?\nA. China\nB. Japan\nC. South Korea\nD. Thailand";
                case 6 -> "What is the largest organ in the human body?\nA. Brain\nB. Skin\nC. Heart\nD. Liver";
                case 7 -> "Who painted the famous artwork 'Starry Night'?\nA. Vincent van Gogh\nB. Pablo Picasso\nC. Leonardo da Vinci\nD. Michelangelo";
                case 8 -> "What is the chemical symbol for the element Gold?\nA. Go\nB. Gd\nC. Au\nD. Ag";
                case 9 -> "Which is the longest river in the world?\nA. Nile\nB. Amazon\nC. Mississippi\nD. Yangtze";
                case 10 -> "Who is the author of the 'Harry Potter' book series?\nA. J.R.R. Tolkien\nB. J.K. Rowling\nC. George R.R. Martin\nD. Stephenie Meyer";
                default -> "";
            };
            }
            case 2 -> {
            return switch (questionIndex) {
                case 1 -> "Which country won the FIFA World Cup in 2018?\nA. Germany\nB. Brazil\nC. France\nD. Argentina";
                case 2 -> "Who is the founder of Microsoft?\nA. Bill Gates\nB. Steve Jobs\nC. Jeff Bezos\nD. Mark Zuckerberg";
                case 3 -> "Which planet is known as the 'Giant of the Solar System'?\nA. Saturn\nB. Uranus\nC. Jupiter\nD. Neptune";
                case 4 -> "Who wrote the famous play 'Romeo and Juliet'?\nA. William Shakespeare\nB. Oscar Wilde\nC. Samuel Beckett\nD. Tennessee Williams";
                case 5 -> "What is the currency of Japan?\nA. Yuan\nB. Euro\nC. Yen\nD. Dollar";
                case 6 -> "What is the chemical symbol for the element Iron?\nA. Ir\nB. Fe\nC. In\nD. Au";
                case 7 -> "Who painted the famous artwork 'The Last Supper'?\nA. Vincent van Gogh\nB. Pablo Picasso\nC. Leonardo da Vinci\nD. Michelangelo";
                case 8 -> "Which is the largest ocean in the world?\nA. Atlantic Ocean\nB. Indian Ocean\nC. Arctic Ocean\nD. Pacific Ocean";
                case 9 -> "Who is the current President of the United States?\nA. Joe Biden\nB. Donald Trump\nC. Barack Obama\nD. George W. Bush";
                case 10 -> "What is the chemical formula for water?\nA. HO2\nB. H2O2\nC. CO2\nD. H2O";
                default -> "";
            };
            }
            case 3 -> {
            return switch (questionIndex) {
                case 1 -> "What is the capital of France?\nA. London\nB. Paris\nC. Rome\nD. Madrid";
                case 2 -> "Who is the CEO of Tesla?\nA. Jeff Bezos\nB. Mark Zuckerberg\nC. Tim Cook\nD. Elon Musk";
                case 3 -> "Which is the largest desert in the world?\nA. Sahara Desert\nB. Gobi Desert\nC. Atacama Desert\nD. Kalahari Desert";
                case 4 -> "Who painted the famous artwork 'The Scream'?\nA. Vincent van Gogh\nB. Pablo Picasso\nC. Leonardo da Vinci\nD. Edvard Munch";
                case 5 -> "What is the currency of Germany?\nA. Euro\nB. Pound\nC. Yen\nD. Dollar";
                case 6 -> "What is the chemical symbol for the element Sodium?\nA. S\nB. Sa\nC. Na\nD. So";
                case 7 -> "Who wrote the famous novel 'Pride and Prejudice'?\nA. Charles Dickens\nB. Jane Austen\nC. Emily Bronte\nD. F. Scott Fitzgerald";
                case 8 -> "What is the highest mountain in the world?\nA. Mount Kilimanjaro\nB. Mount Everest\nC. Mount Fuji\nD. Mount McKinley";
                case 9 -> "Who is the author of the 'The Lord of the Rings' book series?\nA. J.R.R. Tolkien\nB. J.K. Rowling\nC. George R.R. Martin\nD. Stephenie Meyer";
                case 10 -> "What is the chemical formula for table salt?\nA. NaCl\nB. HCl\nC. H2O\nD. CO2";
                default -> "";
            };
            }
            default -> {
                return "";
            }
        }
    }

    private static String getAnswer(int level, int questionIndex) {
        switch (level) {
            case 1 -> {
            return switch (questionIndex) {
                case 1 -> "A";
                case 2 -> "A";
                case 3 -> "C";
                case 4 -> "B";
                case 5 -> "B";
                case 6 -> "B";
                case 7 -> "A";
                case 8 -> "C";
                case 9 -> "A";
                case 10 -> "B";
                default -> "";
            };
            }
            case 2 -> {
            return switch (questionIndex) {
                case 1 -> "C";
                case 2 -> "A";
                case 3 -> "C";
                case 4 -> "A";
                case 5 -> "C";
                case 6 -> "B";
                case 7 -> "A";
                case 8 -> "D";
                case 9 -> "A";
                case 10 -> "D";
                default -> "";
            };
            }
            case 3 -> {
            return switch (questionIndex) {
                case 1 -> "B";
                case 2 -> "D";
                case 3 -> "A";
                case 4 -> "D";
                case 5 -> "A";
                case 6 -> "C";
                case 7 -> "B";
                case 8 -> "B";
                case 9 -> "A";
                case 10 -> "A";
                default -> "";
            };
            }
            default -> {
                return "";
            }
        }
    }

    private static void viewHighestScore() {
        System.out.println("Highest score: " + highestScore);
    }

    private static void resetScore() {
        highestScore = 0;
        System.out.println("Score reset to 0.");
    }

    private static void showHelp() {
        System.out.println("Help:");
        System.out.println("The Quiz Game is a multiple-choice quiz that consists of multiple levels.");
        System.out.println("In each level, you will be asked a series of multiple-choice questions.");
        System.out.println("You need to provide your answer by entering the corresponding letter (A, B, C, or D).");
        System.out.println("You will have a limited time to answer each question, so be quick!");
        System.out.println("To pass a level, you need to answer all the questions correctly within the given time.");
        System.out.println("If you successfully complete all the levels, you will be declared a Quiz Master.");
        System.out.println("Your highest score will be recorded and displayed in the game.");
        System.out.println("Good luck and enjoy the quiz!");
    }
}
