package com.dutq.lock.management.lock_management.entites;

import com.dutq.lock.management.lock_management.entites.passcode.PassCodeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "passcode")
public class PassCode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String passCodeValue;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private PassCodeType type;

  @JsonIgnore private String externalId;
  @JsonIgnore private int lockId;

  @Column(name = "validFrom")
  private LocalDateTime from;

  @Column(name = "validTo")
  private LocalDateTime to;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private long createdDate;

  @JsonIgnore private boolean deletedOnCloud;
}
