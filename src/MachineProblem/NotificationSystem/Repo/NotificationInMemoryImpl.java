package MachineProblem.NotificationSystem.Repo;

import MachineProblem.NotificationSystem.Enums.Status;
import MachineProblem.NotificationSystem.POJO.Notification;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationInMemoryImpl implements  NotificationInMemoryRepository{
    Map<String, Notification> map = new ConcurrentHashMap<>();
    @Override
    public void save(Notification notification) {
        notification.setUpdatedAt(Instant.now());
        map.put(notification.getNotificationId(),notification);
    }

    @Override
    public Notification getNotification(String id) {
        return map.get(id);
    }

    @Override
    public void updateStatus(String id, Status s) {
        Notification notification = map.get(id);
        if(Objects.nonNull(notification)) {
            notification.setStatus(s);
            save(notification);
        }


    }
}
