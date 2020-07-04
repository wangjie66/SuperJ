package spring.common.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import spring.common.response.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalExceptionHandle implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response,
                                         Object o, Exception e) {
        String error = e.getMessage();
        String stack = ExceptionUtils.getStackTrace(e);
        return handleAjaxException(response, error, stack);
    }

    /**
     * 处理 AJAX 请求时的异常: 把异常信息使用 Result 格式化为 JSON 格式，以 AJAX 的方式写入到响应数据中。
     *
     * @param response HttpServletResponse 对象
     * @param error 异常的描述信息
     * @param stack 异常的堆栈信息
     * @return 返回 json，这时 SpringMvc 不会去查找 view，会根据 response 中的信息进行响应。
     */
    private ModelAndView handleAjaxException(HttpServletResponse response, String error, String stack) {
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        JsonResult errorResult = JsonResult.createByErrorMessage(error);
        mv.setView(view);
        mv.addObject("status", errorResult.getStatus());
        mv.addObject("msg", errorResult.getMsg());
     //   mv.addObject("msg", "系统异常,请联系管理员处理!");
        logger.error(error + stack);
        return mv;
    }
}
