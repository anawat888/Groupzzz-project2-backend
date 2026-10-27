package se331.zzz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.zzz.entity.Benefit;
import se331.zzz.entity.Level;
import se331.zzz.entity.ServiceItem;
import se331.zzz.service.BenefitService;
import se331.zzz.service.LevelService;
import se331.zzz.service.ServiceItemService;
import se331.zzz.util.AdminMapper;

@RestController
@RequiredArgsConstructor
public class AdminLevelController {
    final LevelService levelService;
    final BenefitService benefitService;
    final ServiceItemService serviceItemService;

    @GetMapping("/admin/levels")
    public ResponseEntity<?> getLevels() {
        return ResponseEntity.ok(AdminMapper.INSTANCE.getLevelDto(levelService.getLevels()));
    }

    @GetMapping("/admin/levels/{id}")
    public ResponseEntity<?> getLevel(@PathVariable("id") Long id) {
        Level output = levelService.getLevel(id);
        if (output != null) {
            return ResponseEntity.ok(AdminMapper.INSTANCE.getLevelDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PutMapping("/admin/levels/{id}")
    public ResponseEntity<?> updateLevel(@PathVariable("id") Long id, @RequestBody Level level) {
        if (level.getPassScore() == null || level.getPassScore() < 0 || level.getPassScore() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The pass score must be between 0 and 100");
        }
        Level output = levelService.update(id, level);
        if (output != null) {
            return ResponseEntity.ok(AdminMapper.INSTANCE.getLevelDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/admin/levels/{id}/benefits")
    public ResponseEntity<?> addBenefit(@PathVariable("id") Long levelId, @RequestBody Benefit benefit) {
        Benefit output = benefitService.save(levelId, benefit);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AdminMapper.INSTANCE.getBenefitDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given level id is not found");
        }
    }

    @PutMapping("/admin/benefits/{id}")
    public ResponseEntity<?> updateBenefit(@PathVariable("id") Long id, @RequestBody Benefit benefit) {
        Benefit output = benefitService.update(id, benefit);
        if (output != null) {
            return ResponseEntity.ok(AdminMapper.INSTANCE.getBenefitDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @DeleteMapping("/admin/benefits/{id}")
    public ResponseEntity<?> deleteBenefit(@PathVariable("id") Long id) {
        Benefit output = benefitService.delete(id);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/admin/levels/{id}/services")
    public ResponseEntity<?> addService(@PathVariable("id") Long levelId, @RequestBody ServiceItem serviceItem) {
        ServiceItem output = serviceItemService.save(levelId, serviceItem);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(AdminMapper.INSTANCE.getServiceItemDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given level id is not found");
        }
    }

    @PutMapping("/admin/services/{id}")
    public ResponseEntity<?> updateService(@PathVariable("id") Long id, @RequestBody ServiceItem serviceItem) {
        ServiceItem output = serviceItemService.update(id, serviceItem);
        if (output != null) {
            return ResponseEntity.ok(AdminMapper.INSTANCE.getServiceItemDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @DeleteMapping("/admin/services/{id}")
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        ServiceItem output = serviceItemService.delete(id);
        if (output != null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}

