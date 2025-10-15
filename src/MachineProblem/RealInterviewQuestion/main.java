package MachineProblem.RealInterviewQuestion;

import java.time.Duration;
import java.time.Instant;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Solution.VelocityProvider provider = Solution.VelocityProvider.getProvider();

        // Simulate Payments
        Solution.Payment payment1 = new Solution.Payment("P1", Instant.now().minusSeconds(10), "CARD123");
        Solution.Payment payment2 = new Solution.Payment("P2", Instant.now().minusSeconds(30), "CARD123");
        Solution.Payment payment3 = new Solution.Payment("P3", Instant.now().minusSeconds(70), "CARD123");

        provider.registerPayment(payment1);
        provider.registerPayment(payment2);
        provider.registerPayment(payment3);

        // Query usage in last 60 seconds
        System.out.println("Usage in last 60 sec: " + provider.getCardUsageCount(payment1, Duration.ofSeconds(60))); // Output: 2
        System.out.println("Usage in last 100 sec: " + provider.getCardUsageCount(payment1, Duration.ofSeconds(100))); // Output: 3
    }
}
