package FactoryPattern;

import FactoryPattern.Interviewers.Interviewer;

public abstract class HiringManager {
    abstract protected Interviewer assignInterviewer();
    public String takeInterview(){
        Interviewer interviewer = assignInterviewer();
        return interviewer.askQuestion();
    }
}
