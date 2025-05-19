package com.ecole221.l3dev.gestion.scolarite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiliereDto {
  private long id;
  private String libelle;
}
