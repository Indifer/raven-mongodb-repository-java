package raven.mongodb.repository.async;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import raven.mongodb.repository.FindOptions;
import raven.mongodb.repository.entitys.Status;
import raven.mongodb.repository.entitys.User;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class MongoReaderRepositoryAsyncTest {

    private int size = 10;

    @Before
    public void init() throws Exception {

        final CountDownLatch latch = new CountDownLatch(1);
        MongoRepositoryAsyncImpl<User, Long> repos = new UserRepositoryAsyncImpl();
        repos.getDatabase().drop((result, t) -> latch.countDown());

        latch.await();

        ArrayList<User> list = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            User user = new User();
            user.setName(UUID.randomUUID().toString());
            user.setAge(i * 10);

            if (i % 2 == 1) {
                user.setStatus(Status.Delete);
            }

            list.add(user);
        }

        repos.insertBatchAsync(list).join();
    }

    @Test
    public void getList() throws Exception {

        ArrayList<User> list = null;

        MongoReaderRepositoryAsync<User, Long> repos = new UserRepositoryAsyncImpl();
        list = repos.getListAsync(FindOptions.Empty()).get();
        Assert.assertNotEquals(list.size(), 0);

        list = repos.getListAsync(Filters.gte("_id", 1)).get();
        Assert.assertNotEquals(list.size(), 0);

        for (User user : list) {
            if (user.getId() % 2 == 1) {
                Assert.assertEquals(user.getStatus(), Status.Delete);
            }
            Assert.assertNotNull(user.getName());
        }

        list = repos.getListAsync(Filters.eq("_id", 1)).get();
        Assert.assertEquals(list.size(), 1);


        list = repos.getListAsync(null, null, Sorts.descending("_id"), 1, 0).get();
        Assert.assertNotNull(list.get(0));
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getId().longValue(), 10);

    }

    @Test
    public void get() throws Exception {

        MongoRepositoryAsyncImpl<User, Long> repos = new UserRepositoryAsyncImpl();

        User user = null;
        for (long i = 1; i <= size; i++) {
            user = repos.getAsync(i).get();
            Assert.assertNotNull(user);

            user = repos.getAsync(Filters.eq("Name", user.getName())).get();
            Assert.assertNotNull(user);

            user = repos.getAsync(Filters.eq("Name", user.getName()), new ArrayList<String>() {{
                add("_id");
            }}).get();
            Assert.assertEquals(user.getName(), null);
        }

    }

    @Test
    public void count() throws Exception {

        Status status = Status.Normal;
        System.out.println(status == Status.Delete);
        System.out.println(status == Status.Normal);

        MongoRepositoryAsyncImpl<User, Long> repos = new UserRepositoryAsyncImpl();
        Long count = repos.countAsync(Filters.gte("_id", 1)).get();
        Assert.assertEquals(count.longValue(), 10);

        count = repos.countAsync(Filters.gte("_id", 1), 5, null, null).get();
        Assert.assertEquals(count.longValue(), 5);
    }

    @Test
    public void exists() throws Exception {

        MongoRepositoryAsyncImpl<User, Long> repos = new UserRepositoryAsyncImpl();
        Boolean is = repos.existsAsync(Filters.gte("_id", 1)).get();
        Assert.assertTrue(is);

        is = repos.existsAsync(Filters.gte("_id", 11)).get();
        Assert.assertTrue(!is);
    }
}
