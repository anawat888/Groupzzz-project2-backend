package se331.zzz.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ElearningContentDTO {
    Long id;
    String title;
    String description;
    String videoUrl;
}