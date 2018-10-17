package com.passing_parcel.api.server.entity.entityCar;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "category_car")
public class CategoryCar {

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "category_id", length = 2)
    private String categoryId;

    @Column(name = "category_name", length = 50)
    private String categoryName;
}
