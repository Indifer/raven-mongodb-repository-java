package raven.mongodb.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import raven.mongodb.repository.objectID.User_ObjectIDRepository;
import raven.mongodb.repository.stringID.User_StringIDRepository;

public class RepositoryContainerTest {

    @After
    public void init() {
        RepositoryContainer.clear();
    }

    @Test
    public void register() throws Exception {

        UserRepositoryImpl repos = new UserRepositoryImpl();
        RepositoryContainer.register(repos);
        UserRepositoryImpl repos2 = RepositoryContainer.resolve(UserRepositoryImpl.class);

        Assert.assertEquals(repos, repos2);

        RepositoryContainer.register(User2RepositoryImpl.class, () -> new User2RepositoryImpl());
        Assert.assertEquals(RepositoryContainer.resolve(User2RepositoryImpl.class).getClass(), User2RepositoryImpl.class);
    }

    @Test
    public void registerAll() throws Exception {
        String packageName = getClass().getPackage().getName();
        RepositoryContainer.registerAll(packageName);

        UserRepositoryImpl userRepos = RepositoryContainer.resolve(UserRepositoryImpl.class);
        User2RepositoryImpl user2Repos = RepositoryContainer.resolve(User2RepositoryImpl.class);
        MallRepositoryImpl mallRepos = RepositoryContainer.resolve(MallRepositoryImpl.class);

        Assert.assertNotNull(userRepos);
        Assert.assertNotNull(user2Repos);
        Assert.assertNotNull(mallRepos);
        Assert.assertNotNull(RepositoryContainer.resolve(User_ObjectIDRepository.class));
        Assert.assertNotNull(RepositoryContainer.resolve(User_StringIDRepository.class));
        Assert.assertNotNull(RepositoryContainer.resolve(MallRepositoryImpl.MallRepositoryImpl2.class));
        Assert.assertNotNull(RepositoryContainer.resolve(Mall_CreateInstance_RepositoryImpl.class));

        // 测试replace
        RepositoryContainer.replaceAll(packageName);
        // 新注册，跟原对象引用不同
        Assert.assertNotEquals(userRepos, RepositoryContainer.resolve(UserRepositoryImpl.class));
        Assert.assertNotEquals(user2Repos, RepositoryContainer.resolve(User2RepositoryImpl.class));
        Assert.assertNotEquals(mallRepos, RepositoryContainer.resolve(MallRepositoryImpl.class));

        Assert.assertNotNull(RepositoryContainer.resolve(User_ObjectIDRepository.class));
        Assert.assertNotNull(RepositoryContainer.resolve(User_StringIDRepository.class));
        Assert.assertNotNull(RepositoryContainer.resolve(MallRepositoryImpl.MallRepositoryImpl2.class));
        Assert.assertNotNull(RepositoryContainer.resolve(Mall_CreateInstance_RepositoryImpl.class));

    }


}
