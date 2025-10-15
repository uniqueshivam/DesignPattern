package FactoryPattern;

import FactoryPattern.Interviewers.DevelopmentManagerInterviewer;
import FactoryPattern.Interviewers.HrManagerInterviewer;

public class FactoryPattern {
    public static void main(String[] args) {
        DevelopmentManagerInterviewer d = new DevelopmentManagerInterviewer();
        System.out.println(d.takeInterview());
        HrManagerInterviewer hr = new HrManagerInterviewer();
        System.out.println(hr.takeInterview());
    }
}
