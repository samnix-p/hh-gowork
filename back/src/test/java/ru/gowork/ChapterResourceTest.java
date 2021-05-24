package ru.gowork;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@ContextConfiguration(classes = TestConfig.class)
public class ChapterResourceTest extends BaseTest {

  private static final String CONTENT_TEST_DATA = "content_test_data.sql";

  @Inject
  private SessionFactory sessionFactory;

  @Before
  public void fillDB() throws IOException {
      Session session = sessionFactory.openSession();
      Transaction transaction = session.getTransaction();
      transaction.begin();
      try {
        String queries = Files.readString(Paths.get(UNIT_TESTS_SCRIPTS_PATH, CONTENT_TEST_DATA));
        Arrays.stream(queries.split(";")).forEach(query -> session.createNativeQuery(query).executeUpdate());
      } catch (IOException e) {
        e.printStackTrace();
        throw new IOException();
      } finally {
        transaction.commit();
        session.close();
      }
  }

  @After
  public void clearDB() {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.getTransaction();
    transaction.begin();
    session.createNativeQuery("DELETE FROM paragraphs").executeUpdate();
    session.createNativeQuery("DELETE FROM chapters").executeUpdate();
    transaction.commit();
    session.close();
  }

  @Test
  public void checkContentStatus() {
    Response response = executeGet("/content");
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void checkContentData() {
    Response response = executeGet("/content");
    String dataFromDB = response.readEntity(String.class);
    assertEquals("" +
        "[{\"id\":1,\"name\":\"chapter 1\",\"paragraphs\":" +
        "[{\"id\":1,\"name\":\"par 1\"}," +
        "{\"id\":2,\"name\":\"par 2\"}]}," +
        "{\"id\":2,\"name\":\"chapter 2\",\"paragraphs\":" +
        "[{\"id\":3,\"name\":\"par 1\"}]}]",
        dataFromDB);
  }

  @Test
  public void checkUnexpectedContentData() {
    Response response = executeGet("/content");
    String dataFromDB = response.readEntity(String.class);
    assertNotEquals("", dataFromDB);
  }
}
