package hellojpa.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        order.setMember(this);
        this.orders.add(order);
    }
}
