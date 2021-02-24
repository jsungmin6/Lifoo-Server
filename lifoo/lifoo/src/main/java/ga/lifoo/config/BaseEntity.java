package ga.lifoo.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt;

    //    @Getter
//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
//    @Temporal(TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @Column(name = "isDeleted", nullable = false , columnDefinition = "char(1) default 'N'")
    private String isDeleted;
}
