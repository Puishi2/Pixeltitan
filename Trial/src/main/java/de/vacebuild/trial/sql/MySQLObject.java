package de.vacebuild.trial.sql;

public class MySQLObject {

    private String uuid;
    private boolean hasWorld;
    private int status;
    private String reason;
    private String topic;

    public MySQLObject() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isHasWorld() {
        return hasWorld;
    }

    public void setHasWorld(boolean hasWorld) {
        this.hasWorld = hasWorld;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
