package ru.gowork;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class BaseTest extends NabTestBase {

  protected static final String MIGRATIONS_PATH = "migrations";
  protected static final String[] DB_CREATE_FILES = new String[] {
      "001.sql",
      "002-steps-paragraphs.sql",
      "003-users-and-answer.sql"
  };
  protected static final String UNIT_TESTS_SCRIPTS_PATH = "src/test/resources/scripts";

  private static boolean initialized = false;

  @Inject
  private SessionFactory sessionFactory;

  @Before
  public void initDb() throws IOException {
    if (!initialized) {
      Session session = sessionFactory.openSession();
      Transaction transaction = session.getTransaction();
      transaction.begin();
      try {
        for (String file : DB_CREATE_FILES) {
          String queries = Files.readString(Paths.get(MIGRATIONS_PATH, file));
          Arrays.stream(queries.split(";")).forEach(query -> session.createNativeQuery(query).executeUpdate());
        }
      } catch (IOException e) {
        e.printStackTrace();
        throw new IOException();
      } finally {
        transaction.commit();
        session.close();
      }
      initialized = true;
    }
  }

  @Override
  protected NabApplication getApplication() {
    return NabApplication.builder()
        .configureJersey().addAllowedPackages("ru.gowork")
        .bindToRoot()
        .build();
  }
}
