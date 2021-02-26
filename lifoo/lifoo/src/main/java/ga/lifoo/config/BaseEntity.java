package ga.lifoo.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    //    @Getter
//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false, updatable = false)
//    @Temporal(TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "is_deleted", columnDefinition = "CHAR(1) NULL DEFAULT 'N'")
    private String isDeleted;

    @PrePersist
    public void prePersist() {
        this.isDeleted = this.isDeleted == null ? "N" : this.isDeleted;
    }
}
