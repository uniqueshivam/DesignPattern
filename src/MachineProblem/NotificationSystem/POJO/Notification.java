package MachineProblem.NotificationSystem.POJO;

import MachineProblem.NotificationSystem.Enums.Channel;
import MachineProblem.NotificationSystem.Enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Data
@Setter
@Getter
public class Notification {
    private String notificationId; // for idempotency
    private UserDetails userDetails;
    private Channel channel;
    private String template;
    private Map<String, String> templateData;
    private Integer attemptCount;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;

    public Notification(String notificationId, UserDetails userDetails, Channel channel, String template, Map<String, String> templateData, Integer attemptCount, Status status) {
        this.notificationId = notificationId;
        this.userDetails = userDetails;
        this.channel = channel;
        this.template = template;
        this.templateData = templateData;
        this.attemptCount = attemptCount;
        this.status = status;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<String, String> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(Map<String, String> templateData) {
        this.templateData = templateData;
    }

    public Integer getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
