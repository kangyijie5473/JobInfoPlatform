package Service;

import dao.JobDetailDAO;
import entity.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDetailService {
    @Autowired
    private JobDetailDAO jobDetailDAO;

    public int insertJob(JobDetail job) {
        return jobDetailDAO.insertJob(job);
    }

    public int updateJob(JobDetail job) {
        return jobDetailDAO.updateJob(job);
    }
    public List<JobDetail> displayJob() {
        List<JobDetail> list = jobDetailDAO.displayJob();
        return list;
    }
    public JobDetail queryJob(int createId) {

        System.out.println("queryJob");

        JobDetail jd = jobDetailDAO.queryJobByCreateId(createId);
        if(jd == null)
            System.out.println("service null");
        return jd;
    }
    public List<JobDetail> displayJobByid(int id) {
        return jobDetailDAO.displayJobById(id);
    }
    public int deleteJob(int createId) {
        return jobDetailDAO.deleteJobByCreateId(createId);
    }
}
