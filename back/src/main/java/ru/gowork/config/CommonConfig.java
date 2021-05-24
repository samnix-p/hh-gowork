package ru.gowork.config;

import ru.gowork.mapper.ChapterMapper;
import ru.gowork.resource.ChapterResource;
import ru.gowork.dao.ChapterDao;
import ru.gowork.dao.UserDao;
import ru.gowork.resource.StudentResource;
import ru.gowork.resource.ParagraphResource;
import ru.gowork.resource.AuthResource;
import ru.gowork.dao.StudentsDao;
import ru.gowork.dao.ParagraphDao;
import ru.gowork.mapper.StudentMapper;
import ru.gowork.service.ChapterService;
import ru.gowork.service.StudentsService;
import ru.gowork.service.ParagraphService;
import ru.gowork.service.AuthService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;


@Configuration
@Import({
  StudentResource.class,
  StudentsDao.class,
  StudentMapper.class,
  StudentsService.class,
  ParagraphResource.class,
  ParagraphDao.class,
  ParagraphService.class,
  UserDao.class,
  AuthResource.class,
  GwResourceConfig.class,
  AuthService.class,
  ChapterDao.class,
  ChapterResource.class,
  ChapterService.class,
  ChapterMapper.class,
  NabCommonConfig.class
})
public class CommonConfig {
    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.gowork.entity");
        return mappingConfig;
    }
}
