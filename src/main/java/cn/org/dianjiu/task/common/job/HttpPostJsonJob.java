package cn.org.dianjiu.task.common.job;

import cn.org.dianjiu.task.common.util.HttpClientUtils;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.common.job
 * @ClassName: HttpPostJsonJob
 * @Author: MengWei
 * @Description: HTTP PostJson 执行策略
 * @Date: 2020/7/6 22:44
 * @Version: 1.0
 */
public class HttpPostJsonJob implements ExecuteStrategy{

    private String sendUrl;
    private String sendParam;

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getSendParam() {
        return sendParam;
    }

    public void setSendParam(String sendParam) {
        this.sendParam = sendParam;
    }

    public HttpPostJsonJob() {
    }

    public HttpPostJsonJob(String sendUrl, String sendParam) {
        this.sendUrl = sendUrl;
        this.sendParam = sendParam;
    }

    @Override
    public String executeTask() {
        return HttpClientUtils.postJson(sendUrl, sendParam);
    }
}
