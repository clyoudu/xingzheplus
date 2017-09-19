package github.vabshroo.xingzheplus.pojo;

import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username")
    private String username;

    @Column(name = "session_id")
    private String sessionId;

    public Long getUserid() {
        return userid;
    }

    public UserInfo setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public UserInfo setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }
}