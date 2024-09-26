package com.dmdev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SortNatural;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(of = "name")
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OrderBy("username DESC")
    @SortNatural
    private Set<User> users = new TreeSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
