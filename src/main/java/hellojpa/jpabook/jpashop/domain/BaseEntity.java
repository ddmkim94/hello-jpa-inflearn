package hellojpa.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class BaseEntity {

    private String createdBy; // 생성자
    private LocalDateTime createdDate; // 생성일
    private String modifiedBy; // 수정자
    private LocalDateTime lastModifiedDate; // 최종 수정일

}
