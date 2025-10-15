package FactoryPattern.Interviewers;

import FactoryPattern.HiringManager;

public class DevelopmentManagerInterviewer extends HiringManager {
    @Override
    protected Interviewer assignInterviewer() {
        return new Developer();
    }
}
