package hellojpa.jpabook.jpashop.domain;

import hellojpa.jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id") // 안써주면 이름이 이상하게 나옴 (치과 가야함)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
