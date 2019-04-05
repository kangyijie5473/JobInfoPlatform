package controller;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/job/createJob","/job/updateJob", "/job/displayJobSimpleById",
                                                     "/job/deleteJob", "/job/displayJobDetailByCreateId",
                                                     "/job/queryJobDetail", "/job/queryJobSimple"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String userName = (String) ((HttpServletRequest) req).getSession().getAttribute("userName");
        if(userName == null){
            HttpServletResponse response = (HttpServletResponse)resp;
            response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
            response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码        }else {
            PrintWriter out = response.getWriter();//获取PrintWriter输出流
            out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'/>");
            out.write("please login!!!!");
        }else {
            chain.doFilter(req, resp);

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
