package se331.zzz.service;

import se331.zzz.entity.Benefit;
public interface BenefitService {
    Benefit save(Long levelId, Benefit benefit);
    Benefit update(Long id, Benefit benefit);
    Benefit delete(Long id);
}

