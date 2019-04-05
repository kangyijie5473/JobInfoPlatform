package dao;

import entity.JobDetail;

import java.util.List;

public interface JobDetailDAO {
    public int insertJob(JobDetail job);
    public int updateJob(JobDetail job);
    public List<JobDetail> displayJob();
    public JobDetail queryJobByCreateId(int createId);
    public int deleteJobByCreateId(int createId);
    public List<JobDetail> displayJobById(int id);
}
