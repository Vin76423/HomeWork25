package by.tms.entity;

import java.sql.Date;


public class UsersOperation {
    private long userId;
    private Date date = new Date(new java.util.Date().getTime());
    private String num1;
    private String num2;
    private String type;
    private String result;

    public UsersOperation(Date date, String num1, String num2, String type, String result) {
        this.date = date;
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
    }

    public UsersOperation(long userId, String num1, String num2, String type, String result) {
        this.userId = userId;
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
