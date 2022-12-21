package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name="TEAM_ID")
    private List<Member2> member2s = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member2> getMember2s() {
        return member2s;
    }

    public void setMember2s(List<Member2> member2s) {
        this.member2s = member2s;
    }

//    public void addMember(Member2 member2) {
//        member2.setTeam(this);
//        member2.add(member);
//    }
}
