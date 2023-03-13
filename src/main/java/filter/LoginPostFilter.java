package filter;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.code.kaptcha.Constants;
import service.Result;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: stone
 * @program: EasyBuy
 * @description: 用于验证码
 * @date: 2021-11-25 11:00:28
 */
public class LoginPostFilter implements Filter {

    String captchaFieldName = "captcha";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (HttpMethod.OPTIONS.toString().equals(((HttpServletRequest) request).getMethod())){
//            System.out.println("OPTIONS请求，放行");
//            return;
//        }
        if (RequestMethod.POST.name().equals(((HttpServletRequest) request).getMethod())) {
//            String requestCaptcha = request.getParameter("veryCodeInput");
            String requestCaptcha = ((HttpServletRequest) request).getHeader(captchaFieldName);
            String genCaptcha = (String) ((HttpServletRequest) request).getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            System.out.println("开始校验验证码，生成的验证码为：" + genCaptcha + "，输入的验证码为：" + requestCaptcha);

            if (!requestCaptcha.equals(genCaptcha)) {
                System.out.println("验证码错误。");
                showInterceptionInfo((HttpServletResponse) response, -1, null, null);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void showInterceptionInfo(HttpServletResponse response, int status, String msg, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(msg);
        result.setData(data);
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(JSONObject.fromObject(result).toString());
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
