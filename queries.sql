--Part 1
-- id (int, PK), employer(varchar), name(varchar), skills(varchar)

--Part 2
SELECT name FROM employer WHERE location = "St. Louis City";

--Part 3
DROP TABLE job;

--Part 4
SELECT skill.name, skill.description
FROM job_skills
Inner Join job on job.id = job_skills.jobs_id
Inner Join skill on skill.id = job_skills.skills_id
order by name desc