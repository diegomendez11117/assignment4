package org.launchcode.codingevents.models.data;

import org.launchcode.codingevents.models.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SkillRepository extends CrudRepository <Skill,Integer> {
}
