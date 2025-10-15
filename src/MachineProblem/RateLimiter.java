package MachineProblem;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    public static void main(String[] args) {
        Handler handler = new Handler(1,3);
        while(true) {
            try{
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.setRequestTime(Instant.now());
                apiRequest.setUserId("12345");
                handler.handleApiRequest(apiRequest);
            } catch (Exception ex) {
                System.out.println("Waiting for refresh token"+ex.getMessage());
            }

            try {
                Thread.sleep(500); // half a second between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }


    static class ApiRequest {
        Instant requestTime;
        String userId;

        public Instant getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(Instant requestTime) {
            this.requestTime = requestTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    static  class RateCounterUserSpecific{
        double currentRequestCount;
        Instant oldestRequestTime;

        public double getCurrentRequestCount() {
            return currentRequestCount;
        }

        public void setCurrentRequestCount(double currentRequestCount) {
            this.currentRequestCount = currentRequestCount;
        }

        public Instant getOldestRequestTime() {
            return oldestRequestTime;
        }

        public void setOldestRequestTime(Instant oldestRequestTime) {
            this.oldestRequestTime = oldestRequestTime;
        }

        public void refillToken(double maxCapacity, double refillRatePerSecond) {
            long millisElapsed = Instant.now().toEpochMilli() - oldestRequestTime.toEpochMilli();
            double secondsElapsed = millisElapsed / 1000.0;
            double tokensToAdd = secondsElapsed * refillRatePerSecond;
            this.currentRequestCount= (Math.min(maxCapacity,currentRequestCount+tokensToAdd));
            this.oldestRequestTime = Instant.now();

        }
    }

    static class Handler {
        Map<String,RateCounterUserSpecific> map = new ConcurrentHashMap<>();
        final double refillRatePerSecond;
        final double maxLimitAllowed;

        public Handler(double refillRatePerSecond, double maxLimitAllowed) {
            this.refillRatePerSecond = refillRatePerSecond;
            this.maxLimitAllowed = maxLimitAllowed;
        }

        public void handleApiRequest(ApiRequest apiRequest) {
            map.compute(apiRequest.getUserId(), (userId, counter) -> {
                if (counter == null) {
                    counter = new RateCounterUserSpecific();
                    counter.setCurrentRequestCount(1);
                    counter.setOldestRequestTime(Instant.now());
                    System.out.println("Request processed (first request for user)");
                    return counter;
                }

                counter.refillToken(maxLimitAllowed, refillRatePerSecond);
                if (counter.getCurrentRequestCount() <= 0) {
                    throw new RuntimeException("Rate limit exceeded");
                } else {
                    counter.setCurrentRequestCount(counter.getCurrentRequestCount() - 1);
                    System.out.println("Request processed");
                }
                return counter;
            });
        }
    }
}


