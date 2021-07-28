package cn.org.dianjiu.job.common.job;

import cn.org.dianjiu.job.common.util.HttpClientUtils;

import java.util.HashMap;

/**
 * @ProjectName: task-manage
 * @Package: cn.org.dianjiu.task.common.job
 * @ClassName: HttpGetJob
 * @Author: MengWei
 * @Description: HTTP Get 执行策略
 * @Date: 2020/7/6 22:44
 * @Version: 1.0
 */
public class HttpGetJob implements ExecuteStrategy{
    private String sendUrl;
    private HashMap sendParam;

    public HttpGetJob() {
    }

    public HttpGetJob(String sendUrl, HashMap sendParam) {
        this.sendUrl = sendUrl;
        this.sendParam = sendParam;
    }

    public HttpGetJob(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public HashMap getSendParam() {
        return sendParam;
    }

    public void setSendParam(HashMap sendParam) {
        this.sendParam = sendParam;
    }

    @Override
    public String executeTask() {
        return HttpClientUtils.getMap(sendUrl, sendParam);
    }
}
