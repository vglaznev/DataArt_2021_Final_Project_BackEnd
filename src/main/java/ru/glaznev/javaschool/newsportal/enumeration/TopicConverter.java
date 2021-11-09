package ru.glaznev.javaschool.newsportal.enumeration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.glaznev.javaschool.newsportal.exception.NoSuchTopicException;

@Component
public class TopicConverter implements Converter<String, Topic> {
    @Override
    public Topic convert(String content){
            return Topic.of(content);
    }
}
