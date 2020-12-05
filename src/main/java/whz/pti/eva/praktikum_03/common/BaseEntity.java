package whz.pti.eva.praktikum_03.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private PK id;

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode(); }
        return super.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof BaseEntity)) return false;
        BaseEntity<?> other = (BaseEntity<?>) obj;
        return this.getId() != null && this.getId().equals(other.getId()); }


}