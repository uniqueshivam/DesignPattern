package MachineProblem;

import RentalCarBooking.Model.User;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class FileStorageSystem {
    public static void main(String[] args) {
        Handler handler = new Handler();
        String email = "shivam.gmail.com";
        handler.createUser("Shivam",email);
        System.out.println(handler.createFolder(email,"testFolder"));
        handler.createFile("testFile","Intital content","testFolder",email);
        System.out.println(handler.getFolderForUser(email));
        handler.showFilesInFolder(email,"testFolder");
        handler.showFileContent(email,"testFolder","testFile");
        handler.updateFileContent(email,"testFolder","testFile","extra content addition");
        handler.showFileContent(email,"testFolder","testFile");





    }

    static class Folder {
        private String name;
        private List<Files> filesList;
        private Instant createdAt;
        private Instant updatedAt;
        private String linkToShare;
        private Access access;
        private List<UserForFileSystem> userToAccess;

        public Folder(String name, Instant createdAt, Instant updatedAt, String linkToShare, Access access) {
            this.name = name;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.linkToShare = linkToShare;
            this.access = access;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Files> getFilesList() {
            return filesList;
        }

        public void setFilesList(List<Files> filesList) {
            this.filesList = filesList;
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

        public String getLinkToShare() {
            return linkToShare;
        }

        public void setLinkToShare(String linkToShare) {
            this.linkToShare = linkToShare;
        }

        public Access getAccess() {
            return access;
        }

        public void setAccess(Access access) {
            this.access = access;
        }

        public List<UserForFileSystem> getUserToAccess() {
            return userToAccess;
        }

        public void setUserToAccess(List<UserForFileSystem> userToAccess) {
            this.userToAccess = userToAccess;
        }

        @Override
        public String toString() {
            return "Folder{" +
                    "name='" + name + '\'' +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", linkToShare='" + linkToShare + '\'' +
                    '}';
        }
    }

    static class Files {
        private String name;
        private String content;
        private Instant createdAt;
        private Instant updatedAt;
        private Access access;
        private List<User> userToAccess;

        public Files(String name, Instant createdAt, Instant updatedAt, Access access, String content) {
            this.name = name;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.access = access;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public Access getAccess() {
            return access;
        }

        public void setAccess(Access access) {
            this.access = access;
        }

        public List<User> getUserToAccess() {
            return userToAccess;
        }

        public void setUserToAccess(List<User> userToAccess) {
            this.userToAccess = userToAccess;
        }

        @Override
        public String toString() {
            return "Files{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", access=" + access +
                    ", userToAccess=" + userToAccess +
                    '}';
        }
    }

    static class UserForFileSystem {
        private String name;
        private String email;

        public UserForFileSystem(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    enum Access{
        RESTRICTED,
        OPEN,
        USER_GROUP
    }

    static class Handler {
        Map<UserForFileSystem,Map<Folder,List<Files>>> map = new ConcurrentHashMap<>();
        Map<String,UserForFileSystem> userForFileSystemHashSet = new HashMap<>();
        Map<String,Folder> folderMap = new ConcurrentHashMap<>();
        public void createUser(String name, String email) {
            userForFileSystemHashSet.put(email,new UserForFileSystem(name,email));
        }

        public Folder createFolder(String email,String folderName) {
            Folder folder = null;
            if(userForFileSystemHashSet.containsKey(email)) {
                UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
                folder = new Folder(folderName, Instant.now(),Instant.now(),null,Access.RESTRICTED);
                if(map.containsKey(userForFileSystem)) {
                    Map<Folder,List<Files>> folderListMap = map.get(userForFileSystem);
                    if(folderListMap.containsKey(folder)) {
                        throw new RuntimeException("Folder already exist");
                    }

                    folderListMap.put(folder,new ArrayList<>());
                    folderMap.put(email+"/"+userForFileSystem.getName()+"/"+folderName,folder);
                } else {
                    Map<Folder,List<Files>> newFolderMap= new ConcurrentHashMap<>();
                    newFolderMap.put(folder,new ArrayList<>());
                    map.put(userForFileSystem,newFolderMap);
                    folderMap.put(email+"/"+userForFileSystem.getName()+"/"+folderName,folder);
                }
            }
            return folder;
        }

        public void createFile(String name, String content,String folderName,String email) {
            UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
            if(userForFileSystem!=null) {
                Map<Folder,List<Files>> fileFolderList = map.get(userForFileSystem);
                Folder folder = folderMap.get(email+"/"+userForFileSystem.getName()+"/"+folderName);
                if(fileFolderList.containsKey(folder)) {
                    List<Files> filesList = fileFolderList.get(folder);
                    Files newFile = new Files(name,Instant.now(),Instant.now(),Access.RESTRICTED,content);
                    filesList.add(newFile);
                    fileFolderList.put(folder ,filesList);

                } else  {
                    throw new RuntimeException("folder dosen't exist");
                }
            } else  {
                throw  new RuntimeException("User dosen't exist");
            }
        }

        public List<Folder> getFolderForUser(String email) {
            List<Folder> fodlerList = new ArrayList<>();
           UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
           if(userForFileSystem!=null) {
               Map<Folder,List<Files>> folderListMap = map.get(userForFileSystem);
               for(Map.Entry<Folder, List<Files>> entrySet : folderListMap.entrySet()) {
                   fodlerList.add(entrySet.getKey());
               }
           } else  {
               throw new RuntimeException("User not found");
           }
           return fodlerList;
        }

        public void showFilesInFolder(String email, String folderName) {
            UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
            if(userForFileSystem!=null) {
               Folder folder = folderMap.get(email+"/"+userForFileSystem.getName()+"/"+folderName);
                Map<Folder,List<Files>> folderListMap = map.get(userForFileSystem);
                List<Files> filesList = folderListMap.get(folder);
                filesList.forEach(System.out::println);
            }
        }

        public void updateFileContent(String email,String folderName,String fileName, String contentToAdd) {
            UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
            if(userForFileSystem!=null) {
                Map<Folder,List<Files>> folderListMap = map.get(userForFileSystem);
                Folder folder = folderMap.get(email+"/"+userForFileSystem.getName()+"/"+folderName);
                List<Files> filesList = folderListMap.get(folder);
                for(Files files : filesList) {
                    if(Objects.equals(files.getName(), fileName)) {
                        files.setContent(files.getContent() + contentToAdd);
                        break;
                    }
                }
                folderListMap.put(folder,filesList);
            }
        }

        public void showFileContent(String email, String folderName,String fileName) {
            UserForFileSystem userForFileSystem = userForFileSystemHashSet.get(email);
            if(userForFileSystem!=null) {
                Map<Folder,List<Files>> folderListMap = map.get(userForFileSystem);
                Folder folder = folderMap.get(email+"/"+userForFileSystem.getName()+"/"+folderName);
                List<Files> filesList = folderListMap.get(folder);
                for(Files files : filesList) {
                    if(Objects.equals(files.getName(), fileName)) {
                        System.out.println(files.getContent());
                        break;
                    }
                }
            }
        }
    }
}
