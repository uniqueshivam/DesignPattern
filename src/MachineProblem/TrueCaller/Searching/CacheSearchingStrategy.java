package MachineProblem.TrueCaller.Searching;

import MachineProblem.TrueCaller.CacheImplementation.Cache;
import MachineProblem.TrueCaller.Repository.UserRepository;
import MachineProblem.TrueCaller.entities.Contact;

import java.util.List;
import java.util.Objects;

public class CacheSearchingStrategy implements SearchingStrategy{
    public final SearchingStrategy searchingStrategy;
    public final Cache<String, List<Contact>> cache;

    public CacheSearchingStrategy(SearchingStrategy searchingStrategy, Cache<String, List<Contact>> cache) {
        this.searchingStrategy = searchingStrategy;
        this.cache = cache;
    }

    @Override
    public List<Contact> searchPhoneNumber(String query, UserRepository userRepository) {
        List<Contact> contactListFromCache = cache.get(query);
        if(Objects.nonNull(contactListFromCache)){
            return contactListFromCache;
        }
        contactListFromCache = searchingStrategy.searchPhoneNumber(query,userRepository);
        if(Objects.nonNull(contactListFromCache)){
            cache.put(query,contactListFromCache);
        }
        return contactListFromCache;
    }
}
