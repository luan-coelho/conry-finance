package br.com.conry.domain.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class DefaultEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;
    private LocalDateTime creationDate;
    private LocalDateTime changeDate;

    @PrePersist
    protected void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.changeDate = LocalDateTime.now();
    }
}