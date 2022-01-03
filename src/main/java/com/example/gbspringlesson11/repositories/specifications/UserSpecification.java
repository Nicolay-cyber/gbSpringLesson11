package com.example.gbspringlesson11.repositories.specifications;

import com.example.gbspringlesson11.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
