package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transfer_history")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;
    private int price;
    @Column(name = "from_user_id")
    private Long fromUserId;
    private Long toUserId;
}
