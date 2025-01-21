package com.dbinc.cloudoffice.orgtree.security;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 계속 반복되는 속성에 사용
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity 
{
    @CreatedDate
    private LocalDateTime create_dt;
    @LastModifiedDate
    private LocalDateTime update_dt;
}
