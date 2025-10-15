package FactoryPattern.Interviewers;

public class Developer implements  Interviewer{
    @Override
    public String askQuestion() {
        return "I am developer asking questions";
    }
}
