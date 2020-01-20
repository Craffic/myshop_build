package com.Craffic.myshop.jersey.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Getter
@Setter
public class User {
    String id;
    String userName;
    int age;
}
