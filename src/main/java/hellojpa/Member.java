package hellojpa;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@Table(name = "jpql_member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String username;
    private int age;

    @ManyToOne(fetch = LAZY) // 어떤 관계인지?
    @JoinColumn(name = "team_id") // 이 관계에서 조인해야할 컬럼
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // 연관관계 편의 메서드 (양쪽에 값을 설정해주는 메서드)
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "[ID : " + id + "]";
    }
}