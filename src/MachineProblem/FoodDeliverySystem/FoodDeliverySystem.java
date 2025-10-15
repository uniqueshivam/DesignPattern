package MachineProblem.FoodDeliverySystem;


import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FoodDeliverySystem {
    public static void main(String[] args) throws InterruptedException {
        FoodManagementRepo repo = new FoodManagementRepoImpl();
        PaymentFactory paymentFactory = new PaymentFactory();

        Map<String, BlockingQueue<Notification>> notificationMap = new ConcurrentHashMap<>();
        ExecutorService workerPool = Executors.newFixedThreadPool(2);
        NotificationProducer notificationProducer = new NotificationProducer(notificationMap);

        EmailWorker emailWorker = new EmailWorker(workerPool,notificationMap.computeIfAbsent("notification-topic",k->new LinkedBlockingQueue<>()));
        emailWorker.start();

        User user = new User(1, "Alice", "9999999999");
        user.setAddresses(Arrays.asList(new Address("MG Road", 560001)));
        repo.saveUser(user);

        User user2 = new User(2, "Shivam", "9999999879");
        user2.setAddresses(Arrays.asList(new Address("LG Road", 560002)));
        repo.saveUser(user2);

        User user3 = new User(3, "Aditya", "9999999999");
        user3.setAddresses(Arrays.asList(new Address("NG Road", 560003)));
        repo.saveUser(user3);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);



        Restaurant rest = new Restaurant(101, "PizzaHut", new Address("Brigade Road", 560002));
        Menu menu = new Menu("m1");

        FoodItem pizza = new FoodItem("Pizza");
        pizza.setPrice(200);
        FoodItem pasta = new FoodItem("Pasta");
        pasta.setPrice(150);

        menu.setFoodItemList(Arrays.asList(pizza, pasta));

        rest.setMenu(menu);
        repo.saveRestaurant(rest);
        OrderHandler orderHandler = new OrderHandler(repo, paymentFactory.getPaymentStrategy(PaymentMode.CARD), notificationProducer);
        ExecutorService orderPlacing = Executors.newFixedThreadPool(4);
        for(User u : userList) {
            orderPlacing.submit(()->{
                Map<FoodItem, Integer> items = new HashMap<>();
                items.put(pizza, 2);
                items.put(pasta, 1);
                Cart cart = new Cart(u, items);
                orderHandler.placeOrder(cart);

            });
        }

        try { Thread.sleep(200); } catch (InterruptedException e) {}
        workerPool.shutdown();

        orderPlacing.shutdown();

        orderPlacing.awaitTermination(5,TimeUnit.SECONDS);


    }

    static class User {
        private final int id;
        private final String name;
        private final String phoneNumber;
        private List<Address> addresses;
        private List<Order> orderList;

        public User(int id, String name, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public List<Order> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<Order> orderList) {
            this.orderList = orderList;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", addresses=" + addresses +
                    ", orderList=" + orderList +
                    '}';
        }
    }

    static class Restaurant {
        private final int id;
        private final String name;
        private Menu menu;
        private final Address address;

        public Restaurant(int id, String name, Address address) {
            this.id = id;
            this.name = name;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public Address getAddress() {
            return address;
        }
    }

    static class Menu {
        private final String id;
        private List<FoodItem> foodItemList;

        public Menu(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public List<FoodItem> getFoodItemList() {
            return foodItemList;
        }

        public void setFoodItemList(List<FoodItem> foodItemList) {
            this.foodItemList = foodItemList;
        }
    }

    static class FoodItem {
        private final String name;
        private double price;

        public FoodItem(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "FoodItem{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    static class Address {
        private final String area;
        private final int pinCode;

        public Address(String area, int pinCode) {
            this.area = area;
            this.pinCode = pinCode;
        }

        public String getArea() {
            return area;
        }

        public int getPinCode() {
            return pinCode;
        }
    }

    static enum Status {
        PLACED,
        IN_PROGRESS,
        DELIVERED
    }

    static enum PaymentMode {
        CASH,
        CARD
    }

    static class Order {
        private final String orderId;
        private final List<FoodItem> foodItemList;
        private final double totalAmount;
        private final User user;
        private Status orderStatus;

        public Order(String orderId, List<FoodItem> foodItemList, double totalAmount, User user) {
            this.orderId = orderId;
            this.foodItemList = foodItemList;
            this.totalAmount = totalAmount;
            this.user = user;
        }

        public String getOrderId() {
            return orderId;
        }

        public List<FoodItem> getFoodItemList() {
            return foodItemList;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public User getUser() {
            return user;
        }

        public Status getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(Status orderStatus) {
            this.orderStatus = orderStatus;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", foodItemList=" + foodItemList +
                    ", totalAmount=" + totalAmount +
                    ", user=" + user +
                    ", orderStatus=" + orderStatus +
                    '}';
        }
    }

    static class DeliveryPartner {
        private final String id;
        private final String name;
        private final String vehicleNumber;
        private List<Order> orderList;

        public DeliveryPartner(String id, String name, String vehicleNumber) {
            this.id = id;
            this.name = name;
            this.vehicleNumber = vehicleNumber;
        }

        private void assignOrder(Order order) {
            if(this.orderList == null) {
                orderList = new CopyOnWriteArrayList<>();
            }
            orderList.add(order);
            System.out.println("Delivery partner assigned for order "+order.getOrderId());
        }
    }

    static class Cart {
        private final User user;
        private final Map<FoodItem,Integer> foodItemIntegerMap;

        public Cart(User user, Map<FoodItem, Integer> foodItemIntegerMap) {
            this.user = user;
            this.foodItemIntegerMap = foodItemIntegerMap;
        }

        public User getUser() {
            return user;
        }

        public Map<FoodItem, Integer> getFoodItemIntegerMap() {
            return foodItemIntegerMap;
        }
    }
    static enum NotificationType {
        EMAIL,
        SMS
    }

    static class Notification {
        private final Order order;
        private final NotificationType notificationType;

        public Notification(Order order, NotificationType notificationType) {
            this.order = order;
            this.notificationType = notificationType;
        }

        public Order getOrder() {
            return order;
        }

        public NotificationType getNotificationType() {
            return notificationType;
        }
    }

    static interface FoodManagementRepo {
        void saveUser(User user);
        void saveRestaurant(Restaurant restaurant);
        void updateRestaurant(Restaurant restaurant);
        List<Restaurant> getRestaurantByAddress(String pinCode);
        List<Restaurant> getRestaurantByFoodItems(String foodItem);

        void saveOrder(Order order);
        Order getOrderById(String id);

    }

    static class FoodManagementRepoImpl implements  FoodManagementRepo{
        Map<Integer,User> userMap = new ConcurrentHashMap<>();
        Map<Integer, Restaurant> restaurantMap = new ConcurrentHashMap<>();
        Map<String,Order> orderMap = new ConcurrentHashMap<>();
        Map<String,List<Restaurant>> restuarantByFoodMap = new ConcurrentHashMap<>();
        Map<String,List<Restaurant>> restuarantByPinCodeMap = new ConcurrentHashMap<>();

        @Override
        public void saveUser(User user) {
            userMap.put(user.getId(),user);
        }

        @Override
        public void saveRestaurant(Restaurant restaurant) {
            restaurantMap.put(restaurant.getId(),restaurant);
            Menu menu = restaurant.getMenu();
            for(FoodItem foodItem : menu.getFoodItemList()) {
                restuarantByFoodMap.computeIfAbsent(foodItem.getName(),k-> new CopyOnWriteArrayList<>()).add(restaurant);
            }
            restuarantByPinCodeMap.computeIfAbsent(String.valueOf(restaurant.getAddress().getPinCode()),k->new CopyOnWriteArrayList<>()).add(restaurant);
        }

        @Override
        public void updateRestaurant(Restaurant restaurant) {
            // will handle the update later
        }

        @Override
        public List<Restaurant> getRestaurantByAddress(String pinCode) {
            return restuarantByPinCodeMap.get(pinCode);
        }

        @Override
        public List<Restaurant> getRestaurantByFoodItems(String foodItem) {
            return restuarantByFoodMap.get(foodItem);
        }

        @Override
        public void saveOrder(Order order) {
            orderMap.put(order.getOrderId(),order);
        }

        @Override
        public Order getOrderById(String id) {
            return orderMap.get(id);
        }
    }
    static class RestaurantHandler {
        private final FoodManagementRepo foodManagementRepo;

        public RestaurantHandler(FoodManagementRepo foodManagementRepo) {
            this.foodManagementRepo = foodManagementRepo;
        }

        private void registerNewRestaurant(Restaurant restaurant) {
            foodManagementRepo.saveRestaurant(restaurant);
        }
    }

    static class UserHandler {
        private final FoodManagementRepo foodManagementRepo;

        public UserHandler(FoodManagementRepo foodManagementRepo) {
            this.foodManagementRepo = foodManagementRepo;
        }

        private void registerUser(User user) {
            foodManagementRepo.saveUser(user);
        }
    }
    static class OrderHandler {
        private final FoodManagementRepo foodManagementRepo;
        private final PaymentStrategy paymentStrategy;
        private final NotificationProducer notificationProducer;

        public OrderHandler(FoodManagementRepo foodManagementRepo, PaymentStrategy paymentStrategy, NotificationProducer notificationProducer) {
            this.foodManagementRepo = foodManagementRepo;
            this.paymentStrategy = paymentStrategy;
            this.notificationProducer = notificationProducer;
        }

        private void placeOrder(Cart cart) {
            double toPay = 0;
            List<FoodItem> foodItemList = new ArrayList<>();
            Map<FoodItem,Integer> foodItemIntegerMap = cart.foodItemIntegerMap;
            for(Map.Entry<FoodItem,Integer> entry : foodItemIntegerMap.entrySet()) {
                toPay += entry.getKey().getPrice() * entry.getValue();
                foodItemList.add(entry.getKey());
            }
            Order order = new Order(String.valueOf(UUID.randomUUID()),foodItemList,toPay,cart.getUser());
            order.setOrderStatus(Status.PLACED);
            foodManagementRepo.saveOrder(order);
            if (cart.getUser().getOrderList() == null) {
                cart.getUser().setOrderList(new ArrayList<>());
            }
            cart.getUser().getOrderList().add(order);
            paymentStrategy.pay(cart.getUser(),toPay);
            System.out.println("order placed with detail"+order.getOrderId()+" "+order.getFoodItemList());
            Notification notification = new Notification(order,NotificationType.EMAIL);
            notificationProducer.pushNotificationToQueue("notification-topic",notification);
            DeliveryPartner deliveryPartner = new DeliveryPartner(String.valueOf(UUID.randomUUID()),"Anurag","BR01AR8316");
            deliveryPartner.assignOrder(order);

        }

    }


    static class PaymentFactory {
        public PaymentStrategy getPaymentStrategy (PaymentMode paymentMode) {
            switch (paymentMode) {
                case CARD:
                    return new CardPayment();
                default:
                    return new Cash();
            }
        }
    }

    static interface PaymentStrategy {
        public void pay(User user,double amount);
    }
    static class CardPayment implements  PaymentStrategy {

        @Override
        public void pay(User user,double amount) {
            System.out.println("Payed by card");
        }
    }

    static class Cash implements PaymentStrategy {

        @Override
        public void pay(User user,double amount) {
            System.out.println("Payed by cash");
        }
    }
    static interface  searchStrategy {
        List<Restaurant> getResturantList(String queryName,Address addressToDeliver);
    }

    static class searchByPinCodeStrategy implements  searchStrategy {
        private final FoodManagementRepo foodManagementRepo;

        public searchByPinCodeStrategy(FoodManagementRepo foodManagementRepo) {
            this.foodManagementRepo = foodManagementRepo;
        }

        @Override
        public List<Restaurant> getResturantList(String pinCode,Address addressToDeliver) {
            List<Restaurant> restaurantList =  foodManagementRepo.getRestaurantByAddress(pinCode);
            return restaurantList.stream().filter(restaurant -> Math.abs(restaurant.getAddress().getPinCode() - addressToDeliver.getPinCode()) <= 10
            ).collect(Collectors.toList());
        }
    }

    static class searchByFoodNameStrategy implements  searchStrategy {
        private final FoodManagementRepo foodManagementRepo;

        public searchByFoodNameStrategy(FoodManagementRepo foodManagementRepo) {
            this.foodManagementRepo = foodManagementRepo;
        }

        @Override
        public List<Restaurant> getResturantList(String foodName,Address addressToDeliver) {
            List<Restaurant> restaurantList = foodManagementRepo.getRestaurantByFoodItems(foodName);
            return restaurantList.stream().filter(restaurant -> Math.abs(restaurant.getAddress().getPinCode() - addressToDeliver.getPinCode()) <= 10
            ).collect(Collectors.toList());
        }
    }

    static class NotificationProducer {
        private final Map<String, BlockingQueue<Notification>> orderKafkaMap;

        public NotificationProducer(Map<String, BlockingQueue<Notification>> orderKafkaMap) {
            this.orderKafkaMap = orderKafkaMap;
        }

        public void pushNotificationToQueue(String topicName,Notification notification) {
            orderKafkaMap.computeIfAbsent(topicName,k->new LinkedBlockingQueue<>()).offer(notification);
        }
    }

    static abstract class NotificationWorker {
        private final ExecutorService workerPool;
        private final BlockingQueue<Notification> workerSpecificQueue;
        private volatile  boolean running = true;

        public NotificationWorker(ExecutorService workerPool, BlockingQueue<Notification> workerSpecificQueue) {
            this.workerPool = workerPool;
            this.workerSpecificQueue = workerSpecificQueue;
        }

        public abstract void senNotification(Notification notification);

        public void start() {
            workerPool.submit(()->{
                while (running) {
                    try {
                        Notification n = workerSpecificQueue.take();
                        senNotification(n);
                    } catch (Exception ex) {
                        running = false;
                        System.out.println("Exception occurred " + ex.getMessage());
                        throw new RuntimeException(ex);
                    }

                }
            });

        }
    }

    static class EmailWorker extends NotificationWorker {
        public EmailWorker(ExecutorService workerPool, BlockingQueue<Notification> workerSpecificQueue) {
            super(workerPool, workerSpecificQueue);
        }

        @Override
        public void senNotification(Notification notification) {
            System.out.println("Sending email notification "+notification.getOrder().getUser().getName()+" "+notification.getOrder().getOrderId());
        }
    }
}
