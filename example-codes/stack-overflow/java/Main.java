/**
 * ===========================================================
 * Main.java
 * -----------------------------------------------------------
 * Entry point for demo. Shows usage of all core entities.
 * ===========================================================
 */
import Constants.AccountStatus;

public class Main {
    public static void main(String[] args) {
        // Create an account
        AccountTypes.Account account = new AccountTypes.Account(
                "u123", "pass123", "Alice", "NY", "alice@mail.com", "555-9999", AccountStatus.ACTIVE);

        // Create a member
        AccountTypes.Member member = new AccountTypes.Member(account);

        // Post a question
        QuestionEntities.Question question = new QuestionEntities.Question(
                "What is polymorphism?", "I need help understanding OOP.", "Alice");

        // Add an answer
        QuestionEntities.Answer answer = new QuestionEntities.Answer(
                "Polymorphism allows methods to behave differently based on context.", "Bob");
        question.addAnswer(answer);

        // Add a comment
        QuestionEntities.Comment comment = new QuestionEntities.Comment("Nice explanation!", "Charlie");
        question.addComment(comment);

        // Moderate and admin examples
        AccountTypes.Moderator mod = new AccountTypes.Moderator(account);
        mod.closeQuestion(question.search("polymorphism").get(0));

        AccountTypes.Admin admin = new AccountTypes.Admin(account);
        admin.blockMember(member);

        System.out.println("Demo complete.");
    }
}
