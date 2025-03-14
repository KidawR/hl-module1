package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_viewer")
@Getter
@Setter
public class ViewerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public ViewerEntity(long viewerId) {    this.id = viewerId; }


    public ViewerEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public ViewerEntity() {
        
    }

    @Override
    public String toString() {
        return "ViewerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
