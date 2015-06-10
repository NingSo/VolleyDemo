package com.ningso.volleydemo.entity;

/**
 * Created by NingSo on 15/6/10.
 */
public class RecApp {

    public RecApp() {
    }

    /**
     * http://cdn.tifen.com/api/v1/message/apps?pkg= com.yeuxue.tifenapp & channel = tifen
     * name : 360手机助手
     * icon : http://pic.tifen.com/default/9e01fb38e80d11e4a789525400b2a45b.png
     * description : 数十万款Android软件和游戏供您下载，360安全中心检测全方位安全保障
     * id : 55361e7a36ec354186d052a2
     * url : http://openbox.mobilem.360.cn/channel/getUrl?src=cp&app=360box
     */

    private String name;
    private String icon;
    private String description;
    private String id;
    private String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
