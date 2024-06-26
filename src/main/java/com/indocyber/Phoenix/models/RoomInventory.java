package com.indocyber.Phoenix.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomInventories")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "RoomNumber")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "InventoryName")
    private Inventory inventory;
    @Column(name = "Quantity")
    private Integer quantity;
}
