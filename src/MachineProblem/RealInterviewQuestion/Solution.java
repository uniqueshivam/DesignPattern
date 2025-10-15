package MachineProblem.RealInterviewQuestion;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {
    public interface VelocityProvider {

        int getCardUsageCount(Payment payment, Duration duration);

        void registerPayment(Payment payment);

        static VelocityProvider getProvider() {
            return SingletonHolder.INSTANCE;
        }

        class SingletonHolder {
            private static final VelocityProvider INSTANCE = new MyProvider();
        }


        class MyProvider implements VelocityProvider {
            // Store card usage as { hashedCardNumber -> List of transaction timestamps }
            private final Map<String, List<Instant>> cardUsageMap = new ConcurrentHashMap<>();

            @Override
            public void registerPayment(Payment payment) {
                cardUsageMap.computeIfAbsent(payment.getHashedCardNumber(), k -> new ArrayList<>())
                        .add(payment.getTimestamp());
            }

            @Override
            public int getCardUsageCount(Payment payment, Duration duration) {
                Instant referenceTime = payment.getTimestamp(); // Use input timestamp
                Instant cutoff = referenceTime.minus(duration);

                // ✅ Debugging
                System.out.println("Reference Time: " + referenceTime);
                System.out.println("Cutoff Time: " + cutoff);
                System.out.println("Stored Timestamps: " + cardUsageMap.getOrDefault(payment.getHashedCardNumber(), Collections.emptyList()));

                return (int) cardUsageMap.getOrDefault(payment.getHashedCardNumber(), Collections.emptyList())
                        .stream()
                        .filter(time -> !time.isBefore(cutoff)) // ✅ Fix: Count timestamps equal to cutoff
                        .count();
            }

        }
    }

// ✅ Payment Class
static class Payment {
    private final String paymentId;
    private final Instant timestamp;
    private final String hashedCardNumber;

    public Payment(String paymentId, Instant timestamp, String hashedCardNumber) {
        this.paymentId = paymentId;
        this.timestamp = timestamp;
        this.hashedCardNumber = hashedCardNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getHashedCardNumber() {
        return hashedCardNumber;
    }
}
}
