package com.tudoujf.mynotebook.data;

import org.litepal.crud.DataSupport;

/**
 * * ===============================================================
 * name:             TextRecordBean
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/24
 * description：  普通文本记录
 * history：
 * *==================================================================
 */

public class TextRecordBean extends DataSupport {


    /**记录内容*/
    private  String  content;
    /**标题*/
    private  String  title;
    /**保存的序号*/
    private long   saveId;
    /**最后修改时间*/
    private  String  endChangeTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSaveId() {
        return saveId;
    }

    public void setSaveId(long saveId) {
        this.saveId = saveId;
    }

    public String getEndChangeTime() {
        return endChangeTime;
    }

    public void setEndChangeTime(String endChangeTime) {
        this.endChangeTime = endChangeTime;
    }
}
