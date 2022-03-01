package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity
@Getter @Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // Team는 연관관계의 주인이 아님
    private List<Member> members = new ArrayList<>();

    /**
    public void addMember(Member member) {
        this.members.add(member);
        member.setTeam(this);
    }*/
}
