package FactoryPattern.Interviewers;

public class HRTeamInterviewer implements Interviewer{
    @Override
    public String askQuestion() {
        return "I am a HR hence asking bullshit";
    }
}
