package ru.glaznev.javaschool.newsportal.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.glaznev.javaschool.newsportal.exception.NoSuchTopicException;

import java.util.stream.Stream;

public enum Topic {

    POLITICS("Politics"),
    WORLD("World"),
    SOCIETY("Society"),
    FINANCE("Finance"),
    SPORTS("Sports"),
    SCIENCE("Science"),
    JOURNEYS("Journeys");

    private String content;

    private Topic(String content){
        this.content = content;
    }

    @JsonCreator
    public static Topic of(final String content){
        return Stream.of(Topic.values())
                .filter(topic -> topic.content.equals(content))
                .findAny()
                .orElseThrow(() -> new NoSuchTopicException("Topic " + content + " doesn't exist"));
    }

    @JsonValue
    public String getContent(){
        return content;
    }
}
