package com.tech.restaurant_service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "restaurant")
public class Restaurant {

    @Id
    @Field(name = "id", type = FieldType.Keyword)
    private String id;

    @Field(name ="name", type = FieldType.Text)
    private String name;

    @Field(name = "review_count", type = FieldType.Integer)
    private Integer reviewCount;

    @Field(name = "accumulated_rating", type = FieldType.Integer)
    private Integer accumulatedRating;

    @GeoPointField
    @Field(name = "geo_point")
    private GeoPoint geoPoint;
}