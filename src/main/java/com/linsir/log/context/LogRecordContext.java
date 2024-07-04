package com.linsir.log.context;

import com.linsir.log.bean.DiffDTO;
import org.springframework.core.NamedThreadLocal;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 18:12
 * @description：日志记录上下文
 * @modified By：
 * @version: 0.0.1
 */
public class LogRecordContext {

    private static final ThreadLocal<StandardEvaluationContext> CONTEXT_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal StandardEvaluationContext");

    public static final String CONTEXT_KEY_NAME_RETURN = "_return";

    public static final String CONTEXT_KEY_NAME_ERROR_MSG = "_errorMsg";

    public static StandardEvaluationContext getContext() {
        return CONTEXT_THREAD_LOCAL.get() == null ? new StandardEvaluationContext(): CONTEXT_THREAD_LOCAL.get();
    }

    public static void putVariable(String key, Object value) {
        StandardEvaluationContext context = getContext();
        context.setVariable(key, value);
        CONTEXT_THREAD_LOCAL.set(context);
    }

    public static Object getVariable(String key) {
        StandardEvaluationContext context = getContext();
        return context.lookupVariable(key);
    }

    public static void clearContext() {
        CONTEXT_THREAD_LOCAL.remove();
    }


    private static final ThreadLocal<List<DiffDTO>> DIFF_DTO_LIST_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal DiffDTOList");

    public static List<DiffDTO> getDiffDTOList() {
        return DIFF_DTO_LIST_THREAD_LOCAL.get() == null ? new ArrayList<>() : DIFF_DTO_LIST_THREAD_LOCAL.get();
    }

    public static void addDiffDTO(DiffDTO diffDTO) {
        if (diffDTO != null) {
            List<DiffDTO> diffDTOList = getDiffDTOList();
            diffDTOList.add(diffDTO);
            DIFF_DTO_LIST_THREAD_LOCAL.set(diffDTOList);
        }
    }

    public static void clearDiffDTOList() {
        DIFF_DTO_LIST_THREAD_LOCAL.remove();
    }
}
