package controller;

import Service.JobDetailService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.JobDetail;
import entity.JobSimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/job")
@ResponseBody
public class JobDetailController {

    @Autowired
    private JobDetailService jobDetailService;

    @RequestMapping("/createJob")
//    public String createJob(@RequestParam("id") String id) {
    public String createJob(@RequestParam("id") String id, @RequestParam("needNums") String needNums,
                            @RequestParam("skill") String skill, @RequestParam("major") String major,
                            @RequestParam("other") String other, @RequestParam("jobName") String jobName,
                            @RequestParam("companyName") String companyName) {
        System.out.println("call createJOB");
        String result = null;
        JSONObject resultObject = new JSONObject();
        JobDetail jobDetail = new JobDetail();
        Date createDate = new Date(System.currentTimeMillis());
        Date updateDate = createDate;
        jobDetail.setNeedNums(Integer.valueOf(needNums));
        jobDetail.setSkill(skill);
        jobDetail.setMajor(major);
        jobDetail.setOther(other);
        jobDetail.setCreateDate(createDate);
        jobDetail.setUpdateDate(updateDate);
        jobDetail.setId(Integer.valueOf(id));
        jobDetail.setCompanyName(companyName);
        jobDetail.setJobName(jobName);
        jobDetailService.insertJob(jobDetail);

        resultObject.put("createId", jobDetail.getCreateId());
        resultObject.put("result",true);
        result = resultObject.toString();
        System.out.println("create res" + result);
        return result;
    }
    @RequestMapping("/updateJob")
    public String updateJob(@RequestParam("createId") String createId, @RequestParam("needNums") String needNums,
                            @RequestParam("skill") String skill, @RequestParam("major") String major,
                            @RequestParam("other") String other, @RequestParam("jobName") String jobName,
                            @RequestParam("companyName") String companyName) {
        System.out.println("update createId" + major);
        String result = null;
        JSONObject resultObject = new JSONObject();
        JobDetail jobDetail = new JobDetail();
        Date updateDate = new Date(System.currentTimeMillis());
        jobDetail.setNeedNums(Integer.valueOf(needNums));
        jobDetail.setUpdateDate(updateDate);

        jobDetail.setSkill(skill);
        jobDetail.setMajor(major);
        jobDetail.setOther(other);
        jobDetail.setCompanyName(companyName);
        jobDetail.setJobName(jobName);
        jobDetail.setCreateId(Integer.valueOf(createId));
        jobDetailService.updateJob(jobDetail);
//        http://localhost:8080/job/updateJob?createId=1009&skill=a&needNums=2&other=b&companyName=baidu&jobName=cpp&major=d
        resultObject.put("result",true);
        result = resultObject.toString();
        return result;
    }
    @RequestMapping("/displayAllJobSimple")
    public String displayAllJobSimple() {
        List<JobDetail> detailList = jobDetailService.displayJob();
        List<JobSimple> simpleList = new ArrayList<>();
        for(JobDetail jd : detailList) {
            JobSimple js = new JobSimple();
            js.setCompanyName(jd.getCompanyName());
            js.setCreateId(jd.getCreateId());
            js.setId(jd.getId());
            js.setJobName(jd.getJobName());
            js.setMajor(jd.getMajor());
            js.setNeedNums(jd.getNeedNums());
            simpleList.add(js);
        }
        String result = JSON.toJSONString(simpleList);
        System.out.println(result);
        return result;
    }
    @RequestMapping("/displayJobSimpleById")
    public String displayJsById(HttpSession session) {
        int id = 2;
        if (session.getAttribute("id") != null)
            id = (int)session.getAttribute("id");
        System.out.println("call displayJobSimpleByid" + id);
        List<JobDetail> detailList = jobDetailService.displayJobByid(id);
        List<JobSimple> simpleList = new ArrayList<>();
        for(JobDetail jd : detailList) {
            JobSimple js = new JobSimple();
            js.setCompanyName(jd.getCompanyName());
            js.setCreateId(jd.getCreateId());
            js.setId(jd.getId());
            js.setJobName(jd.getJobName());
            js.setMajor(jd.getMajor());
            js.setNeedNums(jd.getNeedNums());
            simpleList.add(js);
        }
        String result = JSON.toJSONString(simpleList);
        return result;
    }
    @RequestMapping("/displayJobDetailByCreateId")
    public String displayJdById(@RequestParam("createId") String createId) {
        System.out.println("call displayJobDetailByCreateId" + createId);
//        List<JobDetail> list = jobDetailService.displayJob();
//        int cid = Integer.valueOf(createId);
//        System.out.println(list.size());
//        JobDetail obj = null;
//        for (JobDetail jd : list) {
//            System.out.println(jd.getCreateId());
//            if (jd.getCreateId() == cid)
//                obj = jd;
//        }
//        System.out.println("aaaaaaaaaaaaa");
//
        JobDetail jd = jobDetailService.queryJob(Integer.valueOf(createId));

        if (jd == null) {
            System.out.println("null");
            return "null";

        }
        String result = JSON.toJSONString(jd);
        System.out.println(result);
        //String result = "{\"companyName\":\"Tencent\",\"createId\":1000,\"id\":1000,\"jobName\":\"C++ RD\",\"major\":\"CS\",\"needNums\":10}";
        return result;
    }
    @RequestMapping("/deleteJob")
    public String deleteJob(@RequestParam("createId") String createId) {
        System.out.println("call deletejob");
        String result = null;
        JSONObject resultObject = new JSONObject();

        if (null == jobDetailService.queryJob(Integer.valueOf(createId))) {
            System.out.println("query null" + createId);
            resultObject.put("result",false);
        } else {
            jobDetailService.deleteJob(Integer.valueOf(createId));
            System.out.println("delete" + createId);

            resultObject.put("result",true);
        }
        result = resultObject.toString();
        System.out.println(result);
        return  result;

    }
    @RequestMapping("/queryJobDetail")
    public String queryJobDetail(@RequestParam("createId") String createId) {
        JobDetail jd = jobDetailService.queryJob(Integer.valueOf(createId));
        String result = JSON.toJSONString(jd);
        return result;
    }
    @RequestMapping("/queryJobSimple")
    public String queryJobSimple(@RequestParam("createId") String createId) {
        JobDetail jd = jobDetailService.queryJob(Integer.valueOf(createId));
        JobSimple js = new JobSimple();
        js.setCompanyName(jd.getCompanyName());
        js.setCreateId(jd.getCreateId());
        js.setId(jd.getId());
        js.setJobName(jd.getJobName());
        js.setMajor(jd.getMajor());
        js.setNeedNums(jd.getNeedNums());
        String result = JSON.toJSONString(js);
        return result;
    }

}
//    function formatDate(date) {
//        dates = date.split("/");
//        if(dates.length == 3) {
//            if(dates[1].length == 1) {
//                dates[1] = "0" + dates[1];
//            }
//            if (dates[2].length == 1) {
//                dates[2] = "0" + dates[2];
//            }
//            date = dates.join("-");
//            return date;
//        } else {
//            return null;
//        }
//    }
//    function parseTime(timestamp) {
//        var date = new Date(parseInt(timestamp)).toLocaleDateString();
//　　//输出结果为2016/8/9
//        date = formatDate(date);
//　　//输出结果为2016-08-09，满足YYYY-MM-DD格式要求
//        return date;
//    }