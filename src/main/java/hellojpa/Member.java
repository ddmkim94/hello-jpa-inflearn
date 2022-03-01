package hellojpa;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member_test")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String username;

//    @Column(name = "team_id")
//    private Long teamId;

    @ManyToOne // 어떤 관계인지?
    @JoinColumn(name = "team_id") // 이 관계에서 조인해야할 컬럼
    private Team team;

    // 연관관계 편의 메서드 (양쪽에 값을 설정해주는 메서드)
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}