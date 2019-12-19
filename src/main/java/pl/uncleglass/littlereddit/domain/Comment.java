package pl.uncleglass.littlereddit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @NonNull
    private String body;
    @ManyToOne
    @NonNull
    private Link link;
}
