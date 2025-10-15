package MachineProblem;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class ParkingLotSystem {
    public static void main(String[] args) {
        Floors floors = new Floors();
        floors.setFloorId(String.valueOf(1));

        List<Lots> lotList = Arrays.asList(
                new Lots(VehicleType.CAR,floors),
                new Lots(VehicleType.CAR,floors),
                new Lots(VehicleType.CAR,floors)
        );

        floors.setLotsList(lotList);
        BoomBarrierAtGate boomBarrierAtGate = new BoomBarrierAtGate();
        ParkingGate entryGate = new EntryGate(boomBarrierAtGate);
        ParkingGate exitGate = new ExitGate(boomBarrierAtGate,null);



        ParkingLot lot = new ParkingLot();
        lot.setId("123");
        lot.setName("Shivam lot");
        lot.setFloorsList(Collections.singletonList(floors));
        lot.setEntryGate(entryGate);
        lot.setExitGate(exitGate);

        //creating user vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("BR01AR8316");
        vehicle.setVehicleType(VehicleType.CAR);
        List<PaymentType> paymentTypeList = Arrays.asList(PaymentType.CARD,PaymentType.UPI);


        User user = new User();
        user.setUserId("1444");
        user.setPaymentTypeList(paymentTypeList);
        user.setVehicleList(Collections.singletonList(vehicle));

        //user 2

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleNumber("BR01AR8319");
        vehicle2.setVehicleType(VehicleType.CAR);
        List<PaymentType> paymentTypeListForUser2 = Arrays.asList(PaymentType.CARD,PaymentType.UPI);


        User user2 = new User();
        user2.setUserId("1449");
        user2.setPaymentTypeList(paymentTypeListForUser2);
        user2.setVehicleList(Collections.singletonList(vehicle2));

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);


        Queue<Lots> lotsQueue = new ConcurrentLinkedQueue<>(lotList);
        SessionManager sessionManager = new SessionManager(900);
        CapacityManager capacityManager = new CapacityManager();
        capacityManager.addLots(VehicleType.CAR,lotsQueue);
        ParkingController parkingController = new ParkingController(lot,capacityManager,sessionManager);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<ParkingSession> parkingSessionList  = Collections.synchronizedList(new ArrayList<>());
        for(User u : userList) {
            executorService.submit(()->{
                try {
                    ParkingSession parkingSession = parkingController.parkVehicle(u, u.getVehicleList().get(0));
                    parkingSessionList.add(parkingSession);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


        try{
            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println("Error");
        }

        for(ParkingSession session : parkingSessionList) {
            executorService.submit(()->{
                try{
                    parkingController.exitVehicle(session,session.getUser().getPaymentTypeList().get(0));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
        }

        executorService.shutdown();


    }

    static class ParkingLot {
        private String id;
        private String name;
        private List<Floors> floorsList;
        private ParkingGate entryGate;
        private ParkingGate exitGate;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Floors> getFloorsList() {
            return floorsList;
        }

        public void setFloorsList(List<Floors> floorsList) {
            this.floorsList = floorsList;
        }

        public ParkingGate getEntryGate() {
            return entryGate;
        }

        public void setEntryGate(ParkingGate entryGate) {
            this.entryGate = entryGate;
        }

        public ParkingGate getExitGate() {
            return exitGate;
        }

        public void setExitGate(ParkingGate exitGate) {
            this.exitGate = exitGate;
        }
    }

    static class Floors {
        private String floorId;
        private List<Lots> lotsList;


        public String getFloorId() {
            return floorId;
        }

        public void setFloorId(String floorId) {
            this.floorId = floorId;
        }

        public List<Lots> getLotsList() {
            return lotsList;
        }

        public void setLotsList(List<Lots> lotsList) {
            this.lotsList = lotsList;
        }
    }

    static enum VehicleType {
        CAR,
        BIKE
    }

    static class Lots {
        private VehicleType vehicleTypeAllowed;
        private Floors floors;

        public Lots(VehicleType vehicleTypeAllowed, Floors floors) {
            this.vehicleTypeAllowed = vehicleTypeAllowed;
            this.floors = floors;
        }

        public VehicleType getVehicleTypeAllowed() {
            return vehicleTypeAllowed;
        }

        public void setVehicleTypeAllowed(VehicleType vehicleTypeAllowed) {
            this.vehicleTypeAllowed = vehicleTypeAllowed;
        }

        public Floors getFloors() {
            return floors;
        }

        public void setFloors(Floors floors) {
            this.floors = floors;
        }
    }

    interface ParkingGate {
        public void openGate();

        public void issueTicket();

        public void closeGate();


    }

    static class EntryGate implements ParkingGate {
        private BoomBarrierAtGate boomBarrierAtGate;

        public EntryGate(BoomBarrierAtGate boomBarrierAtGate) {
            this.boomBarrierAtGate = boomBarrierAtGate;
        }

        @Override
        public void openGate() {
            boomBarrierAtGate.moveUp();
        }

        @Override
        public void issueTicket() {

        }

        @Override
        public void closeGate() {
            boomBarrierAtGate.moveDown();
        }

    }

    static class ExitGate implements ParkingGate {
        private BoomBarrierAtGate boomBarrierAtGate;
        private POSMachine posMachine;

        public ExitGate(BoomBarrierAtGate boomBarrierAtGate, POSMachine posMachine) {
            this.boomBarrierAtGate = boomBarrierAtGate;
            this.posMachine = posMachine;
        }

        @Override
        public void openGate() {

        }

        @Override
        public void issueTicket() {

        }

        @Override
        public void closeGate() {

        }
    }


    static class BoomBarrierAtGate {
        public void moveUp() {
            System.out.println("Boom barrier moved up");
        }

        public void moveDown() {
            System.out.println("Boom barrier moved down");
        }
    }

    static class POSMachine {
        public void invokePOS(double amountToBePaid) {

        }
    }

    static class Vehicle {
        private String vehicleNumber;
        private VehicleType vehicleType;

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }

        public VehicleType getVehicleType() {
            return vehicleType;
        }

        public void setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
        }
    }

    static enum PaymentType {
        CARD,
        UPI,
        CASH
    }

    static class User {
        private String userId;
        private List<Vehicle> vehicleList;
        private List<PaymentType> paymentTypeList;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<Vehicle> getVehicleList() {
            return vehicleList;
        }

        public void setVehicleList(List<Vehicle> vehicleList) {
            this.vehicleList = vehicleList;
        }

        public List<PaymentType> getPaymentTypeList() {
            return paymentTypeList;
        }

        public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
            this.paymentTypeList = paymentTypeList;
        }
    }

    static class ParkingSession {
        private String id;
        private Instant entryTime;
        private Instant exitTime;
        private Lots parkedAtLot;
        private Vehicle vehicle;
        private double sessionAmount;
        private PaymentType paymentType;
        private boolean isActive;
        private User user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Instant getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(Instant entryTime) {
            this.entryTime = entryTime;
        }

        public Instant getExitTime() {
            return exitTime;
        }

        public void setExitTime(Instant exitTime) {
            this.exitTime = exitTime;
        }

        public Lots getParkedAtLot() {
            return parkedAtLot;
        }

        public void setParkedAtLot(Lots parkedAtLot) {
            this.parkedAtLot = parkedAtLot;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public double getSessionAmount() {
            return sessionAmount;
        }

        public void setSessionAmount(double sessionAmount) {
            this.sessionAmount = sessionAmount;
        }

        public PaymentType getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }

    static class CapacityManager {
        private Map<VehicleType, Queue<Lots>> map = new ConcurrentHashMap<>();

        public void addLots(VehicleType type, Queue<Lots> lots) {
            map.putIfAbsent(type, new ConcurrentLinkedQueue<>());
            map.get(type).addAll(lots);
        }

        public Lots isCapacityAvailable(VehicleType vehicleType) {
            Queue<Lots> lotQueue = map.get(vehicleType);

            if (lotQueue == null || lotQueue.isEmpty()) {
                throw new RuntimeException("No available lots for vehicle type: " + vehicleType);
            }

            Lots lot = lotQueue.poll();
            if (lot == null) {
                throw new RuntimeException("No available lots for vehicle type: " + vehicleType);
            }
            return lot;

        }

        public void increaseCapacity(Lots lots) {
            map.computeIfAbsent(lots.getVehicleTypeAllowed(),k->new ConcurrentLinkedQueue<>()).offer(lots);
        }
    }

        static class SessionManager {
            Map<String, List<ParkingSession>> parkingSessionMapForUser = new ConcurrentHashMap<>();
            Map<String, ParkingSession> sessionMap = new ConcurrentHashMap<>();
            private final double baseAmount;

            public SessionManager(double baseAmount) {
                this.baseAmount = baseAmount;
            }

            public ParkingSession startSession(User user, Lots lots, Vehicle vehicle) {
                return parkingSessionMapForUser.compute(user.getUserId(), (id, parkingSessionList) -> {
                    if (Objects.isNull(parkingSessionList)) {
                        parkingSessionList = new CopyOnWriteArrayList<>();
                    }
                    for (ParkingSession session : parkingSessionList) {
                        if (session.isActive) {
                            throw new RuntimeException("Parking session already active");
                        }
                    }

                    ParkingSession createdSession = createParkingSession(lots, user, vehicle, parkingSessionList);
                    sessionMap.put(createdSession.getId(), createdSession);
                    return parkingSessionList;

                }).get(parkingSessionMapForUser.get(user.getUserId()).size() - 1);
            }

            private ParkingSession createParkingSession(Lots lots, User user,Vehicle vehicle,List<ParkingSession> parkingSessionList) {
                ParkingSession createdSession = new ParkingSession();
                createdSession.setActive(true);
                createdSession.setId(UUID.randomUUID().toString());
                createdSession.setParkedAtLot(lots);
                createdSession.setEntryTime(Instant.now());
                createdSession.setVehicle(vehicle);
                createdSession.setUser(user);
                parkingSessionList.add(createdSession);
                sessionMap.put(createdSession.getId(), createdSession);
                return createdSession;
            }


            public boolean closeParkingSession(ParkingSession parkingSession, PaymentStrategy paymentStrategy, TariffStrategy tariffStrategy) {
                //closing logic
                //update the session details
                User user = sessionMap.get(parkingSession.getId()).getUser();
                double amountToPay = tariffStrategy.getAmount(parkingSession, Instant.now(), baseAmount);
                parkingSession.setSessionAmount(amountToPay);
                parkingSession.setExitTime(Instant.now());
                parkingSession.setActive(false);
                return paymentStrategy.makePayment(user, amountToPay);
            }
        }

        static interface PaymentStrategy {
            public boolean makePayment(User user, double amounTobePaid);
        }

        static class CardPayment implements PaymentStrategy {

            @Override
            public boolean makePayment(User user, double amounTobePaid) {
                //user users card payment
                System.out.println("Made payment with card");
                return true;
            }
        }

        static class UpiPayment implements PaymentStrategy {

            @Override
            public boolean makePayment(User user, double amounTobePaid) {
                System.out.println("Made upi payment");
                return true;
            }
        }

        static class CashPayment implements PaymentStrategy {

            @Override
            public boolean makePayment(User user, double amounTobePaid) {
                return false;
            }
        }

        static interface TariffStrategy {
            public double getAmount(ParkingSession parkingSession, Instant exitTime, double baseAmount);

        }

        static class TimeBoundStrategy implements TariffStrategy {
            @Override
            public double getAmount(ParkingSession parkingSession, Instant exitTime, double baseAmount) {
                //do some calculation and return the result
                return 99;
            }
        }

        static class HourlyStrategy implements TariffStrategy {

            @Override
            public double getAmount(ParkingSession parkingSession, Instant exitTime, double baseAmount) {
                // do here the hourly calculation
                return 87;
            }
        }

        static class DefaultTariff implements TariffStrategy {

            @Override
            public double getAmount(ParkingSession parkingSession, Instant exitTime, double baseAmount) {
                //do the calculation here
                return 66;
            }
        }

        static class ParkingController {
            private final ParkingLot parkingLot;
            private final CapacityManager capacityManager;
            private final SessionManager sessionManager;

            public ParkingController(ParkingLot parkingLot, CapacityManager capacityManager, SessionManager sessionManager) {
                this.parkingLot = parkingLot;
                this.capacityManager = capacityManager;
                this.sessionManager = sessionManager;
            }

            public ParkingSession parkVehicle(User user, Vehicle vehicle) {
                Lots lotToPark = capacityManager.isCapacityAvailable(vehicle.getVehicleType());
                if (Objects.isNull(lotToPark)) {
                    throw new RuntimeException("Capacity is not available");
                }
                ParkingSession parkingSession = sessionManager.startSession(user, lotToPark, vehicle);
                if (Objects.nonNull(parkingSession)) {
                    ParkingGate entryGate = parkingLot.getEntryGate();
                    entryGate.openGate();
                    System.out.println("Session started for the user " + user.getUserId());
                } else  {
                    throw new RuntimeException("Parking session not created");
                }
                return parkingSession;

            }

            public void exitVehicle(ParkingSession parkingSession, PaymentType paymentType) {
                parkingSession.setPaymentType(paymentType);
                PaymentStrategy strategy = getPaymentStrategy(paymentType);
                TariffStrategy tariffStrategy = getTariffStrategy(parkingSession);
                if (sessionManager.closeParkingSession(parkingSession, strategy, tariffStrategy)) {
                    ParkingGate parkingGate = parkingLot.exitGate;
                    parkingGate.openGate();
                    capacityManager.increaseCapacity(parkingSession.getParkedAtLot());
                    System.out.println("Parking session closed successfully");
                }
            }

            public PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
                switch (paymentType) {
                    case CARD:
                        return new CardPayment();

                    case UPI:
                        return new UpiPayment();
                    default:
                        return null;
                }
            }

            public TariffStrategy getTariffStrategy(ParkingSession parkingSession) {
                LocalTime time = parkingSession.getEntryTime().atZone(ZoneOffset.UTC).toLocalTime();
                //can be scaled up further
                if (time.isBefore(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(12, 0))) {
                    return new TimeBoundStrategy();
                } else if (!time.isBefore(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(12, 0))) {
                    return new HourlyStrategy();
                } else {
                    return new DefaultTariff();
                }

            }
        }
    }
