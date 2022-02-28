package hellojpa.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    private Long id;
    private Long orderId;
    private Long itemId;
    private int orderPrice;
    private int count;
}
