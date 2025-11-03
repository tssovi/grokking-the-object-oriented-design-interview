/**
 * ===========================================================
 * Question.java
 * -----------------------------------------------------------
 * Models core content entities:
 * - Question
 * - Answer
 * - Comment
 * - Abstract Search base class
 * ===========================================================
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Constants.QuestionStatus;
import Constants.QuestionClosingRemark;

public class QuestionEntities {

    /** Base abstract class defining a search contract. */
    public static abstract class Search {
        public abstract List<String> search(String query);
    }

    /** Represents a question posted by a member. */
    public static class Question extends Search {
        private String title;
        private String description;
        private int viewCount;
        private int voteCount;
        private LocalDateTime creationTime;
        private LocalDateTime updateTime;
        private QuestionStatus status;
        private QuestionClosingRemark closingRemark;
        private String askingMember;
        private List<Comment> comments;
        private List<Answer> answers;

        public Question(String title, String description, String member) {
            this.title = title;
            this.description = description;
            this.askingMember = member;
            this.creationTime = LocalDateTime.now();
            this.updateTime = LocalDateTime.now();
            this.status = QuestionStatus.OPEN;
            this.closingRemark = QuestionClosingRemark.DUPLICATE;
            this.comments = new ArrayList<Comment>();
            this.answers = new ArrayList<Answer>();
        }

        public void close() {
            status = QuestionStatus.CLOSED;
            System.out.println("Question closed: " + title);
        }

        public void undelete() {
            status = QuestionStatus.OPEN;
            System.out.println("Question reopened: " + title);
        }

        public void addComment(Comment comment) {
            comments.add(comment);
            System.out.println("Comment added to question: " + comment.getText());
        }

        public void addAnswer(Answer answer) {
            answers.add(answer);
            System.out.println("Answer added: " + answer.getText());
        }

        @Override
        public List<String> search(String query) {
            List<String> results = new ArrayList<String>();
            if (title.contains(query) || description.contains(query)) {
                results.add(title);
            }
            return results;
        }
    }

    /** Represents a comment posted by a member. */
    public static class Comment {
        private String text;
        private LocalDateTime creationTime;
        private int flagCount;
        private int voteCount;
        private String member;

        public Comment(String text, String member) {
            this.text = text;
            this.member = member;
            this.creationTime = LocalDateTime.now();
            this.flagCount = 0;
            this.voteCount = 0;
        }

        public void incrementVoteCount() {
            voteCount++;
            System.out.println("Comment votes: " + voteCount);
        }

        public String getText() {
            return text;
        }
    }

    /** Represents an answer posted by a member. */
    public static class Answer {
        private String text;
        private boolean accepted;
        private int voteCount;
        private int flagCount;
        private LocalDateTime creationTime;
        private String member;

        public Answer(String text, String member) {
            this.text = text;
            this.member = member;
            this.creationTime = LocalDateTime.now();
        }

        public void incrementVoteCount() {
            voteCount++;
            System.out.println("Answer votes: " + voteCount);
        }

        public String getText() {
            return text;
        }
    }
}
