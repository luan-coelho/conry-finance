package com.conry.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
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