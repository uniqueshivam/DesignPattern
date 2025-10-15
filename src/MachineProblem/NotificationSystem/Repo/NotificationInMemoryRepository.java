package MachineProblem.NotificationSystem.Repo;

import MachineProblem.NotificationSystem.Enums.Status;
import MachineProblem.NotificationSystem.POJO.Notification;

public interface NotificationInMemoryRepository {
    void save(Notification notification);
    Notification getNotification(String id);
    void updateStatus(String id, Status s);

}
