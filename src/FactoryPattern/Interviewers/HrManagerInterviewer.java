package FactoryPattern.Interviewers;

import FactoryPattern.HiringManager;

public class HrManagerInterviewer extends HiringManager {
    @Override
    protected Interviewer assignInterviewer() {
        return new HRTeamInterviewer();
    }
}
