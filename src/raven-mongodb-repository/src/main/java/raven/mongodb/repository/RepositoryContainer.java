package raven.mongodb.repository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author yi.liang
 * @since JDK1.8
 */
public class RepositoryContainer {

    private static ConcurrentHashMap<String, Object> instances;

    static {
        instances = new ConcurrentHashMap<>();
    }

    static void clear() {
        instances.clear();
    }

    /**
     *
     * @param service
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void register(final T service) {
        Class<?> clazz = service.getClass();
        String k = getKey(clazz);

        instances.putIfAbsent(k, service);
    }

    /**
     * @param clazz
     * @param service
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void register(final Class<T> clazz, final Object service) {
        String k = getKey(clazz);

        instances.putIfAbsent(k, service);
    }

    /**
     * @param clazz
     * @param func
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void register(final Class<T> clazz, final Supplier<T> func) {
        String k = getKey(clazz);
        instances.putIfAbsent(k, func.get());
    }

    /**
     * @param packageNames
     */
    public static void registerAll(final String... packageNames) {

        loadAll((clazz, repos) -> {
            if (repos != null) {
                register(clazz, repos);
            }

        }, packageNames);
    }

    /**
     * @param service
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void replace(final T service) {
        Class<?> clazz = service.getClass();
        String k = getKey(clazz);

        instances.replace(k, service);
    }

    /**
     * @param clazz
     * @param service
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void replace(final Class<T> clazz, final Object service) {
        String k = getKey(clazz);

        instances.replace(k, service);
    }

    /**
     *
     * @param clazz
     * @param supplier
     * @param <T>
     */
    public static <T extends MongoBaseRepository> void replace(final Class<T> clazz, final Supplier<T> supplier) {
        String k = getKey(clazz);
        instances.replace(k, supplier.get());
    }

    /**
     *
     * @param packageNames
     */
    public static void replaceAll(final String... packageNames) {

        loadAll((clazz, repos) -> {
            if (repos != null) {
                replace(clazz, repos);
            }

        }, packageNames);
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T extends MongoBaseRepository> T resolve(final Class<T> clazz)
            throws Exception {
        String k = getKey(clazz);
        T repos = (T) instances.get(k);
        if (repos == null) {
            throw new Exception("this repository(" + k + ") is not register");
        }
        return repos;
    }

    /**
     *
     * @param biConsumer
     * @param packageNames
     * @param <T>
     */
    private static <T extends MongoBaseRepository> void loadAll(final BiConsumer<Class<T>, T> biConsumer, final String... packageNames) {

        for (String packageName : packageNames) {

            List<Class> clazzList = Common.getAllClassByInterface(MongoBaseRepository.class, packageName);
            MongoBaseRepository repos;
            for (Class clazz : clazzList) {
                repos = null;
                try {
                    Method method = clazz.getDeclaredMethod(Common.CREATE_INSTANCE_METHOD, null);
                    method.setAccessible(true);
                    repos = (MongoBaseRepository) method.invoke(null, null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (repos == null) {
                    try {
                        repos = (MongoBaseRepository) clazz.newInstance();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                biConsumer.accept(clazz, (T) repos);
                //if (repos != null) {
                //register(clazz, repos);
                //}
            }
        }
    }

    /**
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> String getKey(final Class<T> clazz) {
        return clazz.getName();
    }

}
