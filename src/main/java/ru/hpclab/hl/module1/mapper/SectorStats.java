package ru.hpclab.hl.module1.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SectorStats {
    private Long artistId;
    private String artistName;
    private String sector;
    private Long viewerCount;
}
