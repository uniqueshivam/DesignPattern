package DSAPractise;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        char currentDirection = 'N';
        int currentX = 0;
        int currentY = 0;

        int ans = Integer.MIN_VALUE;
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            set.add(x + "," + y);
        }
        for (int command : commands) {
            switch (command) {
                case -1:
                    if (currentDirection == 'N') {
                        currentDirection = 'E';
                    } else if (currentDirection == 'E') {
                        currentDirection = 'S';
                    } else if (currentDirection == 'S') {
                        currentDirection = 'W';
                    } else if (currentDirection == 'W') {
                        currentDirection = 'N';
                    }
                    break;
                case -2:
                    if (currentDirection == 'N') {
                        currentDirection = 'W';
                    } else if (currentDirection == 'W') {
                        currentDirection = 'S';
                    } else if (currentDirection == 'S') {
                        currentDirection = 'E';
                    } else if (currentDirection == 'E') {
                        currentDirection = 'N';
                    }
                    break;
                default:
                    for (int j = 0; j < command; j++) {
                        if (currentDirection == 'N') {
                            currentY = currentY + 1;
                            if (set.contains(currentX + "," + currentY)) {
                                currentY--;
                                break;
                            }
                        } else if (currentDirection == 'S') {
                            currentY = currentY - 1;
                            if (set.contains(currentX + "," + currentY)) {
                                currentY++;
                                break;
                            }

                        } else if (currentDirection == 'E') {
                            currentX = currentX + 1;
                            if (set.contains(currentX + "," + currentY)) {
                                currentX--;
                                break;
                            }
                        } else if (currentDirection == 'W') {
                            currentX = currentX - 1;
                            if (set.contains(currentX + "," + currentY)) {
                                currentX++;
                                break;
                            }
                        }
                    }
                    ans = Math.max(ans, (currentX*currentX)+ (currentY*currentY));
            }
        }
        return ans;
    }
}
