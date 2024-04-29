package com.mustafa.gendiary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "plan_features")
public class PlanFeatureLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "daily_image_limit")
    private Long dailyImageLimit;

    @Column(name = "plan_uuid")
    private String planUUID;

    @Column(name = "feature_uuid")
    private String featureUUID;
}
