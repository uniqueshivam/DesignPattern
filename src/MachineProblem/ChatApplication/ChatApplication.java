package MachineProblem.ChatApplication;

import java.util.*;
import java.util.concurrent.*;

public class ChatApplication {
    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();

        User user1 = new User("Shivam","8622038942","shivam@rrr","njscsc");
        User user2 = new User("Aditya","8625538942","add@rrr","njs4rcsc");
        User user3 = new User("Rupam","862522942","rup@rrr","njs4r5csc");
        User user4 = new User("dfvdv","8625229442","rurp@rrr","njvss4r5csc");
        User user5 = new User("motit","86112942","rup3@rrr","njjbr5csc");

        userList.add(user1);
        userList.add(user2);
        userList.add(user5);

        Map<String,String> topicMap = new ConcurrentHashMap<>();
        Map<String,User> userTopicDetailMap = new ConcurrentHashMap<>();
        Map<String,Group> groupTopicDetailMap = new ConcurrentHashMap<>();
        ChatRepository chatRepository = new ChatRepositoryImpl(topicMap,userTopicDetailMap,groupTopicDetailMap);
        UserRegistrationHandler userRegistrationHandler = new UserRegistrationHandler(chatRepository);

        for(User user : userList) {
            userRegistrationHandler.userRegistration(user);
        }

        Map<String,BlockingQueue<Chat>> topicChatBlockingQueue = new ConcurrentHashMap<>();
        StartChatHandler startChatHandler  = new StartChatHandler(topicChatBlockingQueue,chatRepository);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            for(int i = 0;i<userList.size();i++) {
                startChatHandler.sendOneToOneMessage(userList.get(i),userList.get(userList.size()-i-1),"Hi user 2 how are you..?");
            }

        });


        for(User user : userList) {
            ReadChatHandler readChatHandler  = new ReadChatHandler(topicChatBlockingQueue,user.getTopic(),chatRepository);
            executorService.submit(readChatHandler);
        }

        try{
            Thread.sleep(9000);
            executorService.shutdown();
            executorService.awaitTermination(6,TimeUnit.SECONDS);
        } catch (Exception ex) {
            System.out.println("error occured "+ex.getMessage());
        }





    }

    static class User {
        private final String name;
        private final String phoneNumber;
        private final String userId;
        private final String appId;
        private String topic;

        public User(String name, String phoneNumber, String userId, String appId) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.userId = userId;
            this.appId = appId;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getUserId() {
            return userId;
        }

        public String getAppId() {
            return appId;
        }

        public String getTopic() {
            return topic;
        }
    }

    static class Chat {
        private final String senderTopic;
        private final String receiverTopic;
        private final String message;

        public Chat(String senderTopic, String receiverTopic, String message) {
            this.senderTopic = senderTopic;
            this.receiverTopic = receiverTopic;
            this.message = message;
        }

        public String getSenderTopic() {
            return senderTopic;
        }

        public String getReceiverTopic() {
            return receiverTopic;
        }

        public String getMessage() {
            return message;
        }
    }

    static class Group {
        private final String groupId;
        private Map<String,User> userListMap;
        private Map<String,User> adminListMap;
        private final String groupTopic;

        public Group(String groupId, String groupTopic, Map<String, User> userList, Map<String, User> adminList) {
            this.groupId = groupId;
            this.groupTopic = groupTopic;
            this.userListMap = userList;
            this.adminListMap = adminList;
        }

        public String getGroupId() {
            return groupId;
        }

        public Map<String, User> getUserListMap() {
            return userListMap;
        }

        public void setUserListMap(Map<String, User> userListMap) {
            this.userListMap = userListMap;
        }

        public Map<String, User> getAdminListMap() {
            return adminListMap;
        }

        public void setAdminListMap(Map<String, User> adminListMap) {
            this.adminListMap = adminListMap;
        }

        public String getGroupTopic() {
            return groupTopic;
        }
    }

    static interface ChatRepository {
        String getTopic(String searchKey);
        void saveTopic(String key, String topic);
        User getUserByTopic(String topic);
        void saveUserWithTopic(User user,String topic);
        void saveGroupWithTopic(String topic, Group group);
        void addUserToGroup(String groupTopic,User user);
    }
    static class ChatRepositoryImpl implements ChatRepository {
        Map<String,String> topicMap = new ConcurrentHashMap<>();
        Map<String,User> userTopicDetailMap = new ConcurrentHashMap<>();
        Map<String,Group> groupTopicDetailMap = new ConcurrentHashMap<>();

        public ChatRepositoryImpl(Map<String, String> topicMap, Map<String, User> userTopicDetailMap, Map<String, Group> groupTopicDetailMap) {
            this.topicMap = topicMap;
            this.userTopicDetailMap = userTopicDetailMap;
            this.groupTopicDetailMap = groupTopicDetailMap;
        }

        @Override
        public String getTopic(String searchKey) {
            return topicMap.get(searchKey);
        }

        @Override
        public void saveTopic(String key, String topic) {
            topicMap.put(key,topic);
        }

        @Override
        public User getUserByTopic(String topic) {
            return userTopicDetailMap.get(topic);
        }

        @Override
        public void saveUserWithTopic(User user, String topic) {
            userTopicDetailMap.put(topic,user);
        }

        @Override
        public void saveGroupWithTopic(String topic, Group group) {
            groupTopicDetailMap.put(topic,group);
        }

        @Override
        public void addUserToGroup(String groupTopic, User user) {
            groupTopicDetailMap.compute(groupTopic,(topic,grp)->{
                if(Objects.nonNull(grp)) {
                    Map<String,User> userMap = grp.getUserListMap();
                    userMap.putIfAbsent(user.getPhoneNumber(),user);
                    return grp;
                }
                return null;
            });
        }
    }

    static class UserRegistrationHandler {
        private final ChatRepository chatRepository;

        public UserRegistrationHandler(ChatRepository chatRepository) {
            this.chatRepository = chatRepository;
        }

        public void userRegistration(User user) {
            String topic  = Util.createTopicName(user,"personal");
            user.setTopic(topic);
            chatRepository.saveUserWithTopic(user,topic);
            chatRepository.saveTopic(user.getPhoneNumber(),topic);
        }

        public void groupCreation(User creator, String groupName) {
            String topic  = Util.createTopicName(creator,groupName);
            Map<String,User> userMap = new ConcurrentHashMap<>();
            userMap.put(creator.getPhoneNumber(),creator);
            Map<String,User> adminMap = new ConcurrentHashMap<>();
            adminMap.put(creator.getPhoneNumber(),creator);
            Group group = new Group(String.valueOf(UUID.randomUUID()), topic,userMap,adminMap);
            chatRepository.saveTopic(groupName,topic);
            chatRepository.saveGroupWithTopic(topic,group);

        }

        public void addMemberToGroup(User user,String groupTopic) {
            chatRepository.addUserToGroup(groupTopic,user);
        }
    }

    static class Util {
        public static String createTopicName(User user, String key) {
            return user.hashCode()+"/"+"chat"+key;
        }
    }

    // i am not storing the chat right now
    static class StartChatHandler {
        private final Map<String,BlockingQueue<Chat>> topicChatBlockingQueue;
        private final ChatRepository chatRepository;

        public StartChatHandler(Map<String, BlockingQueue<Chat>> topicChatBlockingQueue, ChatRepository chatRepository) {
            this.topicChatBlockingQueue = topicChatBlockingQueue;
            this.chatRepository = chatRepository;
        }

        public void sendOneToOneMessage(User sender, User receiver, String message) {
            String receiverTopic = chatRepository.getTopic(receiver.getPhoneNumber());
            if(Objects.isNull(receiverTopic))  {
                throw new RuntimeException("User not found to send message "+receiver.getPhoneNumber());
            }
            Chat chat = new Chat(sender.getTopic(),receiverTopic,message);
            BlockingQueue<Chat> queue = topicChatBlockingQueue.computeIfAbsent(
                    receiverTopic, k -> new LinkedBlockingQueue<>()
            );
            queue.offer(chat);
            System.out.println("Chat sent in the queue");
        }

        public void sentGroupChat(String groupTopic,User sender, String content) {
            String senderTopic = chatRepository.getTopic(sender.getPhoneNumber());
            Chat chat = new Chat(senderTopic,groupTopic, sender.getName());
            topicChatBlockingQueue.compute(groupTopic,(topic,queue)->{
                if(Objects.isNull(queue)) {
                    queue = new LinkedBlockingQueue<>();
                    queue.offer(chat);
                } else  {
                    queue.offer(chat);
                }
                return queue;
            });
            System.out.println("group message sent");

        }
    }

    static class ReadChatHandler implements Runnable {
        private final Map<String,BlockingQueue<Chat>> topicChatBlockingQueue;
        private final String ownerTopic;
        private final ChatRepository chatRepository;
        public volatile  boolean running = true;

        public ReadChatHandler(Map<String, BlockingQueue<Chat>> topicChatBlockingQueue, String ownerTopic, ChatRepository chatRepository) {
            this.topicChatBlockingQueue = topicChatBlockingQueue;
            this.ownerTopic = ownerTopic;
            this.chatRepository = chatRepository;
        }

        @Override
        public void run() {
            while(running) {
                try {
                    BlockingQueue<Chat> queue = topicChatBlockingQueue.computeIfAbsent(
                            ownerTopic, k -> new LinkedBlockingQueue<>()
                    );
                    Chat readChat = queue.poll(1,TimeUnit.SECONDS);
                    User sender = chatRepository.getUserByTopic(readChat.getSenderTopic());
                    //push notification to user
                    System.out.println("Messgae from "+sender.getName()+" "+readChat.getMessage());
                } catch (Exception ex) {
                    Thread.currentThread().interrupt();
                    running = false;
                    System.out.println("Error in read "+ex.getMessage());
                }

            }
        }
    }
}
