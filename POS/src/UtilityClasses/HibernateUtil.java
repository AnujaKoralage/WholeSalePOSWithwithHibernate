package UtilityClasses;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sf = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        File propFile = new File("POS/resources/application.properties");

        // 1. Build the StandardServiceRegistry
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(propFile).build();

        // 2. Build the Metadata
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        // 3. Use those 2 to build the SessionFactory
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

}
