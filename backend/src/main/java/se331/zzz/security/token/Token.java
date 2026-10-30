package se331.zzz.security.token;

import jakarta.persistence.*;
import lombok.*;
import se331.zzz.user.User;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    @Column(unique = true, length = 1024)
    String token;
    boolean revoked;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    User user;
}
