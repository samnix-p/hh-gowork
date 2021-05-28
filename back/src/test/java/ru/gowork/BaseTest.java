package ru.gowork;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import ru.hh.nab.hibernate.transaction.TransactionalScope;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class BaseTest extends NabTestBase {

  private static final String MIGRATIONS_FOLDER = "migrations";

  private static final Path[] DB_CREATE_FILES = new Path[] {
      Paths.get(MIGRATIONS_FOLDER, "001.sql"),
      Paths.get(MIGRATIONS_FOLDER, "002-steps-paragraphs.sql"),
      Paths.get(MIGRATIONS_FOLDER, "003-users-and-answer.sql")
  };

  private static boolean initialized = false;

  @Inject
  private SessionFactory sessionFactory;

  @Inject
  private TransactionalScope transactionalScope;

  @Before
  public void initDb() {
    if (!initialized) {
      transactionalScope.write(() -> {
        Session session = sessionFactory.getCurrentSession();
        try {
          for (Path file : DB_CREATE_FILES) {
            String queries = Files.readString(file);
            Arrays.stream(queries.split(";"))
                .forEach(query -> session.createNativeQuery(query).executeUpdate());
          }
        } catch (IOException e) {
          throw new RuntimeException(e.getMessage());
        }
      });
      initialized = true;
    }
  }

  @After
  public void clearDb() {
    transactionalScope.write(() -> {
      Session session = sessionFactory.getCurrentSession();
      session.createNativeQuery("DELETE FROM paragraphs").executeUpdate();
      session.createNativeQuery("DELETE FROM chapters").executeUpdate();
    });
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder()
        .configureJersey().addAllowedPackages("ru.gowork")
        .bindToRoot()
        .build();
  }
}
