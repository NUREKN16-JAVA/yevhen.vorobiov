package ua.nure.vorobiov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

    protected static final String USER_DAO = "dao.ua.nure.vorobiov.usermanagement.db.UserDao";
    private static final String CONNECTION_DRIVER = "connection.driver";
    private static final String CONNECTION_URL = "connection.url";
    private static final String CONNECTION_USER = "connection.user";
    private static final String CONNECTION_PASSWORD = "connection.password";
    private static final String PROPERTIES_FILE = "settings.properties";
    private static final String DAO_FACTORY = "dao.factory";

    private static DaoFactory instance;

    protected static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream(
                    PROPERTIES_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DaoFactory() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            try {
                Class factoryClass = Class.forName(properties
                        .getProperty(DAO_FACTORY));
                instance = (DaoFactory) factoryClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public void init(Properties properties) {
        this.properties = properties;
        instance = null;
    }

    public abstract UserDao getUserDao();

    protected ConnectionFactory getConnectionFactory() {
        String driver = properties.getProperty(CONNECTION_DRIVER);
        String url = properties.getProperty(CONNECTION_URL);
        String user = properties.getProperty(CONNECTION_USER);
        String password = properties.getProperty(CONNECTION_PASSWORD);
        return new ConnectionFactoryImpl(driver, url, user, password);
    }
}
