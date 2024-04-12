package com.ua.project.Autobase.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "users_roles")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
