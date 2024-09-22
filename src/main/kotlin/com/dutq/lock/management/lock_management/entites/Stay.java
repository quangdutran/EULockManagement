package com.dutq.lock.management.lock_management.entites;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stay")
public class Stay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String customerName;
  private String customerPhone;
  private String customerEmail;

  private LocalDateTime checkInTime;
  private LocalDateTime expectedCheckoutTime;
  private LocalDateTime actualCheckoutTime;

  private int lockId;
  private double price;
}
