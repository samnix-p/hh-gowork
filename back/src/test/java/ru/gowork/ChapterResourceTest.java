package ru.gowork;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.hibernate.transaction.TransactionalScope;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = TestConfig.class)
public class ChapterResourceTest extends BaseTest {

  @Inject
  private SessionFactory sessionFactory;

  @Inject
  private TransactionalScope transactionalScope;

  @Test
  public void checkContentStatus() {
    Response response = executeGet("/content");
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void checkContentData() throws JsonProcessingException {
    transactionalScope.write(() -> {
      sessionFactory.getCurrentSession()
          .createNativeQuery("INSERT INTO chapters (id, name) VALUES (1, 'chapter 1')")
          .executeUpdate();
      sessionFactory.getCurrentSession()
          .createNativeQuery("INSERT INTO chapters (id, name) VALUES (2, 'chapter 2')")
          .executeUpdate();
      sessionFactory.getCurrentSession()
          .createNativeQuery("INSERT INTO paragraphs (id, name, chapter_id) VALUES (1, 'par 1', 1)")
          .executeUpdate();
      sessionFactory.getCurrentSession()
          .createNativeQuery("INSERT INTO paragraphs (id, name, chapter_id) VALUES (2, 'par 2', 1)")
          .executeUpdate();
      sessionFactory.getCurrentSession()
          .createNativeQuery("INSERT INTO paragraphs (id, name, chapter_id) VALUES (3, 'par 1', 2)")
          .executeUpdate();
    });

    Response response = executeGet("/content");
    HashSet<Object> dataFromDB = response.readEntity(new GenericType<HashSet<Object>>(){});
    HashSet<Object> expectedValue = new ObjectMapper()
        .readValue("" +
            "[{\"id\":1,\"name\":\"chapter 1\",\"paragraphs\":" +
            "[{\"id\":1,\"name\":\"par 1\"},{\"id\":2,\"name\":\"par 2\"}]}," +
            "{\"id\":2,\"name\":\"chapter 2\",\"paragraphs\":" +
            "[{\"id\":3,\"name\":\"par 1\"}]}]",
            new TypeReference<HashSet<Object>>() {});

    assertEquals(expectedValue, dataFromDB);
  }
}
