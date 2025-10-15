package MachineProblem.OrderManagementSystem;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

public class OrderManagementSystem {
    public static void main(String[] args) {

        User user1 = new User(String.valueOf(UUID.randomUUID()),"shivam@gmail.com","8765676777","ui0op");
        User user2 = new User(String.valueOf(UUID.randomUUID()),"shivamm@gmail.com","87656476777","ui0o7p");
        Map<ModeOfPayment,String> modeOfPaymentDetailsUser1 = new HashMap<>();
        modeOfPaymentDetailsUser1.put(ModeOfPayment.CARD,"9876543");
        user1.setModeOfPaymentDetails(modeOfPaymentDetailsUser1);

        Map<ModeOfPayment,String> modeOfPaymentDetailsUser2 = new HashMap<>();
        modeOfPaymentDetailsUser1.put(ModeOfPayment.UPI,"98765");
        user2.setModeOfPaymentDetails(modeOfPaymentDetailsUser2);

        Item item1 = new Item(String.valueOf(UUID.randomUUID()),"Iphone17",876888,"It's an iphone",ProductType.ELECTRONICS);
        Item item2 = new Item(String.valueOf(UUID.randomUUID()),"Levis jeans",1000,"It's a jeans",ProductType.CLOTHING);
        Item item3 = new Item(String.valueOf(UUID.randomUUID()),"Biriyani",400,"It's a Biriyani",ProductType.FOOD);
        Item item4 = new Item(String.valueOf(UUID.randomUUID()),"Razer",10,"It's a razer",ProductType.CARE);

        Offer offer = new Offer(String.valueOf(UUID.randomUUID()),"50 RS flat",OfferType.FLAT,50);
        Offer offer2 = new Offer(String.valueOf(UUID.randomUUID()),"50%",OfferType.PERCENTAGE,50);

        Map<String, List<String>> orderDetailsOfuserMap = new ConcurrentHashMap<>();
        Map<String,Order> orderMap = new ConcurrentHashMap<>();
        BlockingQueue<Order> orderBlockingQueue = new LinkedBlockingQueue<>();

        OrderManagementRepository orderManagementRepository = new OrderManagementRepoImpl();

        OrderPlacingHandler orderPlacingHandler = new OrderPlacingHandler(orderBlockingQueue,orderManagementRepository);

        AdminHandler adminHandler = new AdminHandler(orderBlockingQueue,orderManagementRepository);

        Map<Item,Integer> user1Purchase = new ConcurrentHashMap<>();
        user1Purchase.put(item1,1);
        user1Purchase.put(item3,4);

        Map<Item,Integer> user2Purchase = new ConcurrentHashMap<>();
        user1Purchase.put(item2,1);
        user1Purchase.put(item4,7);


        Cart user1Cart = new Cart(user1Purchase,null);
        Cart use2Cart = new Cart(user2Purchase,offer2);
        user1.setCart(user1Cart);
        user2.setCart(use2Cart);

        double amount = orderPlacingHandler.calculateAmount(user1);
        System.out.println("Amount to be paid for user "+user1.getId()+" "+amount);

        Order placedOrder = orderPlacingHandler.makePaymentAndPlaceOrder(amount,user1,ModeOfPayment.CARD,user1Cart);
        System.out.println("Placed order "+placedOrder.getId());

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            executorService.submit(new NotificationHandler(orderBlockingQueue));

            Thread.sleep(7000);
            executorService.submit(()-> adminHandler.updateStatus(placedOrder.getId(),Status.SHIPPED));

            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception ex) {
            Thread.currentThread().interrupt();
            System.out.println();
        }

    }

    static class User {
        private final String id;
        private final String email;
        private final String phoneNumber;
        private final String userAppId;
        private Cart cart;
        private Map<ModeOfPayment,String> modeOfPaymentDetails;

        public User(String id, String email, String phoneNumber, String userAppId) {
            this.id = id;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.userAppId = userAppId;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getUserAppId() {
            return userAppId;
        }


        public Cart getCart() {
            return cart;
        }

        public void setCart(Cart cart) {
            this.cart = cart;
        }

        public Map<ModeOfPayment, String> getModeOfPaymentDetails() {
            return modeOfPaymentDetails;
        }

        public void setModeOfPaymentDetails(Map<ModeOfPayment, String> modeOfPaymentDetails) {
            this.modeOfPaymentDetails = modeOfPaymentDetails;
        }
    }

    static class Cart {
        private Map<Item,Integer> itemIntegerMap;
        //i can extend it for offer
        private Offer offer;

        public Cart(Map<Item, Integer> itemIntegerMap, Offer offer) {
            this.itemIntegerMap = itemIntegerMap;
            this.offer = offer;
        }

        public Map<Item, Integer> getItemIntegerMap() {
            return itemIntegerMap;
        }

        public void setItemIntegerMap(Map<Item, Integer> itemIntegerMap) {
            this.itemIntegerMap = itemIntegerMap;
        }

        public Offer getOffer() {
            return offer;
        }

        public void setOffer(Offer offer) {
            this.offer = offer;
        }
    }

    static class Offer {
        private String offerId;
        private String offerDescription;
        private OfferType offerType;
        private Integer value;

        public Offer(String offerId, String offerDescription, OfferType offerType, Integer value) {
            this.offerId = offerId;
            this.offerDescription = offerDescription;
            this.offerType = offerType;
            this.value = value;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public String getOfferDescription() {
            return offerDescription;
        }

        public void setOfferDescription(String offerDescription) {
            this.offerDescription = offerDescription;
        }

        public OfferType getOfferType() {
            return offerType;
        }

        public void setOfferType(OfferType offerType) {
            this.offerType = offerType;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    static enum OfferType {
        FLAT,
        PERCENTAGE
    }


    static  class Order {
        private final String id;
        private final Instant orderedDate;
        private final double totalAmount;
        private final String paymentGatewayReferenceId;
        private Status status;
        private ModeOfPayment modeOfPayment;
        private Map<Item,Integer> itemListCountMap;
        private User user;

        public Order(String id, Instant orderedDate, double totalAmount, String paymentGatewayReferenceId) {
            this.id = id;
            this.orderedDate = orderedDate;
            this.totalAmount = totalAmount;
            this.paymentGatewayReferenceId = paymentGatewayReferenceId;
        }

        public String getId() {
            return id;
        }

        public Instant getOrderedDate() {
            return orderedDate;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public String getPaymentGatewayReferenceId() {
            return paymentGatewayReferenceId;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public ModeOfPayment getModeOfPayment() {
            return modeOfPayment;
        }

        public void setModeOfPayment(ModeOfPayment modeOfPayment) {
            this.modeOfPayment = modeOfPayment;
        }

        public Map<Item, Integer> getItemListCountMap() {
            return itemListCountMap;
        }

        public void setItemListCountMap(Map<Item, Integer> itemListCountMap) {
            this.itemListCountMap = itemListCountMap;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    static enum Status {
        PLACED,
        PENDING,
        SHIPPED,
        DELIVERED,
        REFUNDED
    }
    static enum ModeOfPayment  {
        CARD,
        UPI,
        CASH
    }
    static enum ProductType {
        ELECTRONICS,
        FOOD,
        CLOTHING,
        CARE
    }
    static class Item {
        private final String id;
        private final String name;
        private final double price;
        private final String description;
        private final ProductType productType;

        public Item(String id, String name, double price, String description, ProductType productType) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.productType = productType;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }


    }

    static interface PaymentStrategy {
        public String pay(double amountToPay,String paymentMethodDetails);
    }

    static class CardPayment implements PaymentStrategy {

        @Override
        public String pay(double amountToPay, String paymentMethodDetails) {
            System.out.println("Paying with card");
            return "jsvbchj";
        }
    }

    static class UPIPayment implements PaymentStrategy {

        @Override
        public String pay(double amountToPay, String paymentMethodDetails) {
            System.out.println("Payment done with card");
            return "iuyhfg";
        }
    }

    static class CashPayment implements PaymentStrategy {

        @Override
        public String pay(double amountToPay, String paymentMethodDetails) {
            System.out.println("payment made by cash");
            return "";
        }
    }

    static interface OfferStrategy {
        public double applyOffer(double amountToPay,Map<Item,Integer> itemCountMap);
    }

    static class FlatOfferStrategy implements OfferStrategy {

        @Override
        public double applyOffer(double amountToPay, Map<Item, Integer> itemCountMap) {
            //have some logic for calculating the price
            return 90;
        }
    }

    static class PercentageOfferStrategy implements OfferStrategy {

        @Override
        public double applyOffer(double amountToPay, Map<Item, Integer> itemCountMap) {
           //have some logic here for calculating price
            return 87;
        }
    }

    static interface  OrderManagementRepository {
        void save(Order order);
        void updateOrderAgainstUse(User user, Order order);
        void updateOrder(Order order);
        Order getOrder(String orderId);
    }

    static class OrderManagementRepoImpl implements OrderManagementRepository {
        Map<String, List<String>> orderDetailsOfuserMap = new ConcurrentHashMap<>();
        Map<String,Order> orderMap = new ConcurrentHashMap<>();

        @Override
        public void save(Order order) {
            orderMap.put(order.getId(),order);
        }

        @Override
        public void updateOrderAgainstUse(User user, Order order) {
            orderDetailsOfuserMap.compute(user.id, (userDetail,productList)->{
                List<String> list;
                if(Objects.isNull(productList)) {
                    list = Collections.synchronizedList(new ArrayList<>());
                    list.add(order.getId());
                    return list;
                } else  {
                    productList.add(order.getId());
                    return productList;
                }
            });


        }

        @Override
        public void updateOrder(Order order) {
            orderMap.put(order.getId(),order);

        }

        @Override
        public Order getOrder(String orderId) {
            return orderMap.get(orderId);
        }
    }

    static class OrderPlacingHandler {
        private final BlockingQueue<Order> orderBlockingQueue;
        private final OrderManagementRepository orderManagementRepo;

        public OrderPlacingHandler(BlockingQueue<Order> orderBlockingQueue, OrderManagementRepository orderManagementRepo) {
            this.orderBlockingQueue = orderBlockingQueue;
            this.orderManagementRepo = orderManagementRepo;
        }

        public double calculateAmount(User user) {
            Cart cart = user.getCart();
            Offer offer = cart.getOffer();
            double amountToPay = 0;

            //handle concurrency here
            Map<Item,Integer> itemMap = cart.itemIntegerMap;
            for(Map.Entry<Item,Integer> entry : itemMap.entrySet()) {
                int count = entry.getValue();
                Item item = entry.getKey();
                amountToPay = amountToPay+(item.getPrice()*count);
            }
            if(Objects.nonNull(offer)) {
                OfferStrategy offerStrategy = getOfferStrategy(offer.getOfferType());
                amountToPay = offerStrategy.applyOffer(amountToPay,itemMap);
            }
            return amountToPay;

        }

        public Order makePaymentAndPlaceOrder(double amountToPay, User user,ModeOfPayment modeOfPayment,Cart cart) {
            PaymentStrategy paymentStrategy = getPaymentStrategy(modeOfPayment);
            String gatewayReferenceId = paymentStrategy.pay(amountToPay,user.getModeOfPaymentDetails().get(modeOfPayment));
            String orderId = String.valueOf(UUID.randomUUID());
            Order newOrder = new Order(orderId,Instant.now(),amountToPay,gatewayReferenceId);
            System.out.println("Order created");
            newOrder.setStatus(Status.PLACED);
            newOrder.setUser(user);
            newOrder.setItemListCountMap(cart.getItemIntegerMap());
            newOrder.setModeOfPayment(modeOfPayment);

            orderManagementRepo.updateOrderAgainstUse(user,newOrder);

            //this should be concurrent hashmap
           orderManagementRepo.save(newOrder);
           orderBlockingQueue.offer(newOrder);
            //handle the failure cases as well
            return newOrder;

        }

        private PaymentStrategy getPaymentStrategy(ModeOfPayment modeOfPayment) {
            switch (modeOfPayment) {
                case CARD:
                    return new CardPayment();
                case UPI:
                    return new UPIPayment();
                default:
                    return new CashPayment();
            }
        }

        private OfferStrategy getOfferStrategy(OfferType offerType) {
            // i am assuming offer type will be of 2 type always that's why not handling default case
            switch (offerType) {
                case FLAT:
                    return new FlatOfferStrategy();
                case PERCENTAGE:
                    return new PercentageOfferStrategy();
                default:
                    return null;
            }
        }
    }

    static class NotificationHandler implements  Runnable {
        private BlockingQueue<Order> orderBlockingQueue;

        public NotificationHandler(BlockingQueue<Order> orderBlockingQueue) {
            this.orderBlockingQueue = orderBlockingQueue;
        }

        @Override
        public void run() {
            try{
                while(!Thread.currentThread().isInterrupted()) {
                    Order order = orderBlockingQueue.take();
                    User user = order.getUser();
                    //push different types of notification to the user with order details
                    System.out.println("Notification sent for the order "+order.getId()+" "+order.getStatus());
                }
            } catch (Exception ex) {
                Thread.currentThread().interrupt();
                System.out.println("Exception occurred in notification handler "+ex.getMessage());
            }
        }

        //i am not handling the notification system, that can be scaled differently to support multile types of notification.
    }


    static class AdminHandler {
        private BlockingQueue<Order> orderBlockingQueue;
        private OrderManagementRepository orderManagementRepo;

        public AdminHandler(BlockingQueue<Order> orderBlockingQueue, OrderManagementRepository orderManagementRepo) {
            this.orderBlockingQueue = orderBlockingQueue;
            this.orderManagementRepo = orderManagementRepo;
        }

        public void updateStatus(String orderId, Status status) {
            Order order = orderManagementRepo.getOrder(orderId);
            order.setStatus(status);
            orderBlockingQueue.offer(order);
        }

        public void refund(String orderId) {
            //i am assuming all order will be refunded al together not item wise, this can be scaled up
            Order updateOrder = orderManagementRepo.getOrder(orderId);
            String gatewayOrderReferenceId = updateOrder.getPaymentGatewayReferenceId();
            boolean refundStaus = true;
            if(refundStaus) {
                updateOrder.setStatus(Status.REFUNDED);
            }
            orderManagementRepo.updateOrder(updateOrder);
            orderBlockingQueue.offer(updateOrder);
        }

    }


}
