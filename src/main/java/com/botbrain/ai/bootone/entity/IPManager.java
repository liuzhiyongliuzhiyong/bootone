package com.botbrain.ai.bootone.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @描述：TODO  代理IP实体类
 * @作者：LZY
 * @日期：2019/4/13 11:05
 * @版本：1.0
 **/
public class IPManager implements Serializable {

    private static final long serialVersionUID=1L;

    //代理IP地址
    private String ip;
    //代理IP端口
    private Integer port;
    //代理IP用户名
    private String username;
    //代理IP密码
    private String password;
    //代理IP所属城市
    private String ip_city;
    //代理IP匿名程度
    private String ip_type;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp_city() {
        return ip_city;
    }

    public void setIp_city(String ip_city) {
        this.ip_city = ip_city;
    }

    public String getIp_type() {
        return ip_type;
    }

    public void setIp_type(String ip_type) {
        this.ip_type = ip_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IPManager ipManager = (IPManager) o;
        return Objects.equals(ip, ipManager.ip) &&
                Objects.equals(port, ipManager.port) &&
                Objects.equals(username, ipManager.username) &&
                Objects.equals(password, ipManager.password) &&
                Objects.equals(ip_city, ipManager.ip_city) &&
                Objects.equals(ip_type, ipManager.ip_type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ip, port, username, password, ip_city, ip_type);
    }

    @Override
    public String toString() {
        return "IPManager{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ip_city='" + ip_city + '\'' +
                ", ip_type='" + ip_type + '\'' +
                '}';
    }
}
